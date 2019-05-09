/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.persistencia;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.dto.TipoUsuarioDTO;
import com.pizzaria.dto.UsuarioDTO;
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
public class UsuarioDAO extends BaseDAO {

    @Override
    public List<BaseDTO> buscar(Criterio c) {
        
        int iTimeout = 60;
        StringBuilder query = new StringBuilder(); 
        query.append("SELECT u.id, nome, senha, t.descricao as descricao, email, login from ");
        query.append(super.obterNomeMapeamentoTabela(c.getObjetoBusca().getClass())); 
        query.append(" u INNER JOIN TipoUsuario t ON t.id = u.idTipoUsuario ");
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
           
 
        Connection conn = ConexaoDAO.conectar();
        List<BaseDTO> listaBaseDAO =  new ArrayList<BaseDTO>();        
        try {
            Statement stmt = conn.createStatement();
            
            stmt.setQueryTimeout(iTimeout);
            ResultSet rs = stmt.executeQuery(query.toString());

            while(rs.next()) {
                UsuarioDTO obj = new UsuarioDTO();      
                TipoUsuarioDTO t = new TipoUsuarioDTO();
                 t.setDescricao(rs.getString("descricao"));
                ((UsuarioDTO) obj).setId(rs.getInt("id"));
                ((UsuarioDTO) obj).setNome(rs.getString("nome"));
                ((UsuarioDTO) obj).setSenha(rs.getString("senha"));
                ((UsuarioDTO) obj).setTipo(t);
                ((UsuarioDTO) obj).setEmail(rs.getString("email"));
                ((UsuarioDTO) obj).setUsuario(rs.getString("login"));
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
