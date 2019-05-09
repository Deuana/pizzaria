/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.persistencia;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.dto.ProdutoDTO;
import com.pizzaria.utilitarios.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Angelmário
 */
public class PedidoDAO extends BaseDAO {

    @Override
    public boolean salvar(BaseDTO modelo) {
        return super.salvar(modelo); 
    }

    @Override
    public List<BaseDTO> buscar(Criterio c) {
        return super.buscar(c); 
    }

    /***
     * Método para obter pedidos cadastrados no banco de dados.
     * O método monta um select para trazer as informações.
     * @param c
     * @return 
     */
    public List<BaseDTO> obterPedidos(Criterio c) {
         int iTimeout = 60;
        String tabela = "";
        StringBuilder query = new StringBuilder(); 

        query.append("SELECT p.id, idCliente, c.nome, pp.idProduto, pd.descricao, formaPagamento, p.valor, pedidoFinalizado, pedidoPronto, timeInsert from ");
        query.append(super.obterNomeMapeamentoTabela(c.getObjetoBusca().getClass()));  
        query.append(" p ");
        query.append(" INNER JOIN PedidoProduto pp ON p.id = pp.idPedido ");
        query.append(" INNER JOIN Clientes c ON p.idCliente = c.id ");
        query.append(" INNER JOIN  Produtos pd ON pd.id = pp.idProduto ");   
        if(!Utils.isNullOrEmpty(c.getCriterios())){
            query.append(" WHERE ");
            for (Map.Entry<String, String> entrySet : c.getCriterios().entrySet()) {
                if(!entrySet.getValue().isEmpty()){
                    query.append(entrySet.getKey()).append(" ").append( entrySet.getValue());                                                                
                }
                query.append(" AND ");                
            }
            if(query.toString().endsWith((" AND "))){
                query.replace(query.lastIndexOf((" AND ")), query.length(), "");
            }                        
        }
        query.append(" ORDER BY timeInsert ASC ");
        Connection conn = ConexaoDAO.conectar();
        List<BaseDTO> listaBaseDAO =  new ArrayList<BaseDTO>();        
        try {
            Statement stmt = conn.createStatement();
            
            stmt.setQueryTimeout(iTimeout);
            ResultSet rs = stmt.executeQuery(query.toString());

            while(rs.next()){          
                PedidoDTO obj = new PedidoDTO();
                ClienteDTO cl = new ClienteDTO();
                cl.setId(rs.getInt("idCliente"));
                cl.setNome(rs.getString("nome"));
                ((PedidoDTO) obj).setCliente(cl);
                ((PedidoDTO) obj).setId(rs.getInt("id"));
                ((PedidoDTO) obj).setFormaPagamento(rs.getString("formaPagamento"));
                ((PedidoDTO) obj).setValor(rs.getBigDecimal("valor").setScale(2));                                                
                ((PedidoDTO) obj).setTimeInsert(rs.getTimestamp("timeInsert"));
                ((PedidoDTO) obj).setPedidoFinalizado(rs.getBoolean("pedidoFinalizado"));
                ((PedidoDTO) obj).setPedidoPronto(rs.getBoolean("pedidoPronto"));
                ProdutoDTO p = new ProdutoDTO();
                 //Só vai ter 1
                p.setId(rs.getInt("idProduto"));
                p.setDescricao(rs.getString("descricao"));
                List<ProdutoDTO> lst = new ArrayList<ProdutoDTO>();
                lst.add(p);
                ((PedidoDTO) obj).setProduto(lst);
                listaBaseDAO.add(obj);                 
                   
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

 
    
}
