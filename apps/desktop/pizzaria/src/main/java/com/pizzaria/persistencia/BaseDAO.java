/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.persistencia;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.dto.PedidoProdutoDTO;
import com.pizzaria.dto.ProdutoDTO;
import com.pizzaria.dto.TipoUsuarioDTO;
import com.pizzaria.dto.UsuarioDTO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.utilitarios.Utils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Adiel
 */
public abstract class BaseDAO {
    private static final String CLIENTES = "CLIENTES";
    private static final String TIPO_USUARIO = "TipoUsuario";
    private static final String PEDIDOS = "Pedidos";
    private static final String PRODUTOS = "Produtos";
    private static final String USUARIOS = "Usuarios";
    private static final String PEDIDO_PRODUTO = "PedidoProduto";
    /***
     * Método genério para salvar dados no banco, com base na sua instância.
     * O método verifica qual a instância que está sendo passada e a partir disto,
     * ele monta a estrutura do INSERT com seus respectivos campos do banco de dados.
     * @param modelo
     * @return boolean
     */
    public boolean salvar(BaseDTO modelo) {
        //modelo - instância do BaseDTO que determinadas classes extendem.
        int iTimeout = 60;
        boolean inserido = false;
        Timestamp datahoje = null;
        StringBuilder query = new StringBuilder();
        if(modelo instanceof UsuarioDTO){
            query.append("INSERT INTO ");
            query.append(USUARIOS);
            query.append(" (nome, email, senha, login, idTipoUsuario) VALUES (");
            query.append("'").append(((UsuarioDTO) modelo).getNome()).append("',");
            query.append("'").append(((UsuarioDTO) modelo).getEmail()).append("',");            
            query.append("'").append(((UsuarioDTO) modelo).getSenha().toLowerCase()).append("',");
            query.append("'").append(((UsuarioDTO) modelo).getUsuario().toLowerCase()).append("',");
            query.append(((UsuarioDTO) modelo).getTipo().getId());
            query.append(");");
        }else if(modelo instanceof ClienteDTO){
            query.append("INSERT INTO ");
            query.append(CLIENTES);
            query.append(" (nome, telefone, endereco) VALUES (");
            query.append("'").append(((ClienteDTO) modelo).getNome()).append("',");
            query.append("'").append(((ClienteDTO) modelo).getTelefone()).append("',");            
            query.append("'").append(((ClienteDTO) modelo).getEndereco()).append("'");
            query.append(");");
        } else if (modelo instanceof PedidoDTO){
            datahoje = new Timestamp(System.currentTimeMillis());
            query.append("INSERT INTO ");
            query.append(PEDIDOS);
            query.append(" (idCliente, formaPagamento, valor, pedidoFinalizado, pedidoPronto, timeInsert) VALUES (");
            query.append( ((PedidoDTO) modelo).getCliente().getId()).append(",");
            query.append("'").append(((PedidoDTO) modelo).getFormaPagamento()).append("',");            
            query.append( ((PedidoDTO) modelo).getValor()).append(",");
            query.append(0).append(",");
            query.append(0).append(",");
            query.append("'").append(datahoje).append("'");
            query.append(");");
        } else if (modelo instanceof PedidoProdutoDTO){
            datahoje = new Timestamp(System.currentTimeMillis());
            query.append("INSERT INTO ");
            query.append(PEDIDO_PRODUTO);
            query.append(" (idPedido, idProduto, horapedido) VALUES (");
            query.append( ((PedidoProdutoDTO) modelo).getIdPedido()).append(",");
            query.append( ((PedidoProdutoDTO) modelo).getIdProduto()+ ",");            
            query.append("'").append(((PedidoProdutoDTO) modelo).getHorapedido()).append("'");
            query.append(");");
        } else if (modelo instanceof ProdutoDTO){
            query.append("INSERT INTO ");
            query.append(PRODUTOS);
            query.append(" (descricao, valor) VALUES (");
            query.append("'").append(((ProdutoDTO) modelo).getDescricao()).append("',");            
            query.append(((ProdutoDTO) modelo).getValor());
            query.append(");");
        }
        
        Connection conn = ConexaoDAO.conectar();
      
        try {                
               Statement stmt = conn.createStatement();            
               stmt.setQueryTimeout(iTimeout);

                if(stmt.executeUpdate(query.toString()) > 0){
                    inserido = true;
                }
                if(modelo instanceof PedidoDTO){
                    Criterio criterio = new Criterio(new PedidoDTO());
                    criterio.setCriterio("timeInsert", " = '" + datahoje + "'");
                    BaseDTO singleResultPedido = buscar(criterio).get(0);
                    for (ProdutoDTO p : ((PedidoDTO) modelo).getProduto()) {
                        PedidoProdutoDTO pp = new PedidoProdutoDTO();
                        pp.setHorapedido(((PedidoDTO)singleResultPedido).getTimeInsert());
                        pp.setIdPedido(((PedidoDTO)singleResultPedido).getId());
                        pp.setIdProduto(p.getId());
                        this.salvar(pp);
                    }
                }
            
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try{ 
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return inserido;
    }
    
    /***
     * Método genérico para efetuar buscas no banco de dados com base em critérios.
     * Utilizando o objeto Criterio, ele obtém as informações necessárias para efetuar a busca
     * no banco de dados. As informações que são fornecidas seriam:
     * 1. Nome da tabela para efetuar a busca, que é obtido através da anotação na classe da instância fornecia;
     * 2. (Opcional) Condições para a busca, que é dado por: campo e valor.
     * @param c
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> buscar(Criterio c)  {        
        
        int iTimeout = 60;
        String tabela = "";
        StringBuilder query = new StringBuilder(); 
        tabela = obterNomeMapeamentoTabela(c.getObjetoBusca().getClass());
        query.append("SELECT ").append(obterCamposByMapeamentoCampo(c.getObjetoBusca().getClass())).append(" from ");
        query.append(tabela);            
        if(!Utils.isNullOrEmpty(c.getCriterios())){
            query.append(" WHERE ");
            for (Map.Entry<String, String> entrySet : c.getCriterios().entrySet()) {
                if(!entrySet.getValue().isEmpty()){
                    query.append(entrySet.getKey()).append(" ").append(entrySet.getValue());                                                                
                }
                query.append(" AND ");                
            }
            if(query.toString().endsWith((" AND "))){
                query.replace(query.lastIndexOf((" AND ")), query.length(), "");
            }                        
        }
           
 
        Connection conn = ConexaoDAO.conectar();
        List<BaseDTO> listaBaseDAO =  new ArrayList<BaseDTO>();        
        try {
            Statement stmt = conn.createStatement();
            
            stmt.setQueryTimeout(iTimeout);
            ResultSet rs = stmt.executeQuery(query.toString());

            while(rs.next())
                {
                    
                    if(tabela.equalsIgnoreCase(TIPO_USUARIO)){
                        TipoUsuarioDTO obj = new TipoUsuarioDTO();
//
//                        Class cls = c.getObjetoBusca().getClass();
//                        Object object = cls.newInstance();
//                        Field listaCampo[] = c.getObjetoBusca().getClass().getDeclaredFields(); // array que pega os
//                        Method mtd[] = c.getObjetoBusca().getClass().getDeclaredMethods();
//                        // campos                           
//                        for (Method m : mtd){                           
//                               if(m.getName().startsWith("set")){
//                                   System.out.println(m.getName());                                                                    
//                               }
//                        }
//                        for (Field fld : listaCampo) {
//                            if(fld.getType().equals(String.class)){
//                                fld.(obj, rs.getString(teste(fld)));
//                            }
//                             if(fld.getType().equals(Integer.class)){
//                                fld.set(obj, rs.getInt(teste(fld)));
//                            }
//                        }       
//                        c.getObjetoBusca().getClass().getDeclaredFields();
                        ((TipoUsuarioDTO) obj).setDescricao(rs.getString("descricao"));
                        ((TipoUsuarioDTO) obj).setId(rs.getInt("id"));
                        
                        listaBaseDAO.add((BaseDTO) obj);
                    }else if(tabela.equalsIgnoreCase(PRODUTOS)){
                        ProdutoDTO obj = new ProdutoDTO();
                        ((ProdutoDTO) obj).setDescricao(rs.getString("descricao"));
                        ((ProdutoDTO) obj).setId(rs.getInt("id"));
                        ((ProdutoDTO) obj).setValor(rs.getBigDecimal("valor").setScale(2));
                        ((ProdutoDTO) obj).setQuantidade(rs.getInt("quantidade"));
                        listaBaseDAO.add(obj);
                    } else if(tabela.equalsIgnoreCase(CLIENTES)){
                        ClienteDTO obj = new ClienteDTO();
                        ((ClienteDTO) obj).setNome(rs.getString("nome"));
                        ((ClienteDTO) obj).setId(rs.getInt("id"));
                        ((ClienteDTO) obj).setTelefone(rs.getString("telefone"));
                        ((ClienteDTO) obj).setEndereco(rs.getString("endereco"));
                        listaBaseDAO.add(obj);                     
                    } else if(tabela.equalsIgnoreCase(PEDIDOS)){
                        PedidoDTO obj = new PedidoDTO();
                        ClienteDTO cl = new ClienteDTO();
                        cl.setId(rs.getInt("idCliente"));    
                        ((PedidoDTO) obj).setCliente(cl);
                        ((PedidoDTO) obj).setId(rs.getInt("id"));
                        ((PedidoDTO) obj).setPedidoFinalizado(rs.getBoolean("pedidoFinalizado"));
                        ((PedidoDTO) obj).setPedidoPronto(rs.getBoolean("pedidoPronto"));
                        ((PedidoDTO) obj).setFormaPagamento(rs.getString("formaPagamento"));
                        ((PedidoDTO) obj).setValor(rs.getBigDecimal("valor").setScale(2));                                                
                        ((PedidoDTO) obj).setTimeInsert(rs.getTimestamp("timeInsert"));
                        listaBaseDAO.add(obj);   
                    }
                   
                }

        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return listaBaseDAO;
    }
    
    /***
     * Método genérico para atualizar dados no banco, com base na sua instância.
     * Utilizando o objeto fornecido, ele obtém informações necessárias para efetuar UPDATE no 
     * banco de dados.
     * @param modelo
     * @return boolean
     * @throws Exception 
     */
    public boolean atualizar(BaseDTO modelo) throws Exception{
       int iTimeout = 60;
       boolean atualizado = false;
        String tabela = "";
        StringBuilder query = new StringBuilder(); 
        tabela = obterNomeMapeamentoTabela(modelo.getClass());
        query.append("UPDATE ").append(tabela).append(" SET ");
        query.append(obterCamposAndInforByMapeamentoCampo(modelo.getClass(), modelo));                
        Connection conn = ConexaoDAO.conectar();
      
        try {              
               Statement stmt = conn.createStatement();            
               stmt.setQueryTimeout(iTimeout);

                if(stmt.executeUpdate(query.toString()) > 0){
                    atualizado = true;
                }
              
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try{ 
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
        return atualizado;
    }
    
//    protected String teste(Field fld) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//        return fld.getAnnotations()[0].annotationType().getDeclaredMethods()[0].invoke(fld.getAnnotations()[0]).toString(); 
//    }    
    
    /***
     * Método para obter nome dos campos através das anotações na classe passada como argumento.
     * Observação: Método utilizado preferêncialmente no SELECT.
     * @param cls
     * @return String
     */
    protected String obterCamposByMapeamentoCampo(Class cls){
        StringBuffer campos = new StringBuffer();
        try {
                // classe
                Object obj = cls.newInstance();
                Field listaCampo[] = cls.getDeclaredFields(); // array que pega os
                // campos                               
                for (Field fld : listaCampo) { 
                    Annotation[] annotations = fld.getAnnotations();
                    for (Annotation a : annotations) {
                        //System.out.println("Annotation utilizada: " + a);
                        for (Method m : a.annotationType().getDeclaredMethods()) {
                            //System.out.println("Nome do método(" + a.annotationType().getName() + "): " + m.getName());
                            Object o = m.invoke(a);
                            //System.out.println("Valor: " + o.toString());
                            campos.append(o.toString()).append(", ");
                        }
                    }
                }
                if(!Utils.isNullOrEmpty(campos)){
                    if(campos.toString().endsWith((", "))){
                        campos.replace(campos.lastIndexOf((", ")), campos.length(), "");
                    }
                }
        } catch (Throwable e) {
                System.err.println(e);
        }
        return campos.toString();

    }

    /***
     * Método para obter o nome dos campos e suas informações a partir dos argumentos fornecidos.
     * Observação: Método utilizado preferêncialmente no UPDATE.
     * @param cls
     * @param modelo
     * @return 
     */
    protected String obterCamposAndInforByMapeamentoCampo(Class cls, BaseDTO modelo){
        StringBuilder campos = new StringBuilder();
        StringBuilder condId = new StringBuilder();
        
        Class noparams[] = {};
        try {
                // classe
                Object obj = cls.newInstance();
                Field listaCampo[] = cls.getDeclaredFields(); // array que pega os
               
                // campos                               
                for (Field fld : listaCampo) { 
                    Annotation[] annotations = fld.getAnnotations();
                    for (Annotation a : annotations) {
                        //System.out.println("Annotation utilizada: " + a);
                        for (Method m : a.annotationType().getDeclaredMethods()) {
                            //System.out.println("Nome do método(" + a.annotationType().getName() + "): " + m.getName());
                            Object o = m.invoke(a);
                            //System.out.println("Valor: " + o.toString());
                            
                            if(("id").equalsIgnoreCase(o.toString())){
                                condId.append(o.toString()).append(" = ");
                            }else{
                                campos.append(o.toString()).append(" = ");   
                            }
                            for (Method mt : cls.getDeclaredMethods()){                           
                                if((mt.getName().startsWith("get") || mt.getName().startsWith("is")) && mt.getName().toLowerCase().endsWith(fld.getName().toLowerCase())){
                                   Method method = cls.getDeclaredMethod(mt.getName(), noparams);
                                   if(mt.getReturnType().equals(String.class) || mt.getReturnType().equals(Timestamp.class)){
                                       campos.append("'").append(method.invoke(modelo, null).toString()).append("'");
                                       campos.append(", ");
                                   }else if(mt.getReturnType().equals(Boolean.class) ){
                                       if((Boolean.TRUE).equals((Boolean)method.invoke(modelo, null))){
                                            campos.append("1");
                                       }else{
                                           campos.append("0");
                                       }
                                       campos.append(", ");
                                   }else if(BaseDTO.class.isAssignableFrom(mt.getReturnType())){
                                       campos.append(((BaseDTO) method.invoke(modelo, null)).getId());
                                        campos.append(", ");
                                   }else{
                                       if(o.toString().toLowerCase().equals("id")){
                                           condId.append(method.invoke(modelo, null).toString());
                                       }else{
                                            campos.append(method.invoke(modelo, null).toString());
                                            campos.append(", ");
                                       }
                                       
                                   }
                                   
                                   break;
                                }                                
                            }
                        }
                    }
                }
                if(!Utils.isNullOrEmpty(campos)){
                    if(campos.toString().endsWith((", "))){
                        campos.replace(campos.lastIndexOf((", ")), campos.length(), "");
                    }
                    campos.append(" WHERE ").append(condId.toString());
                    
                }
        } catch (Throwable e) {
                System.err.println(e);
        }
        return campos.toString();

    }

    /***
     * Método para obter nome da tabela do banco, através das anotações da classe passsada.
     * @param cls
     * @return String
     */
    protected String obterNomeMapeamentoTabela(Class cls){
        String nomeTabela = null;
        try{
            nomeTabela = cls.getAnnotations()[0].annotationType().getDeclaredMethods()[0].invoke(cls.getAnnotations()[0]).toString(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        return nomeTabela;

    }
    
    
    
}