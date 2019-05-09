package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.UsuarioDTO;
import com.pizzaria.persistencia.UsuarioDAO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.utilitarios.Utils;
import java.util.List;

/**
 *
 * @author Angelm�rio
 */
public class UsuarioRN {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    /***
     * M�todo para salvar usuario, efetuando suas respectivas valida��es.
     * @param usuarioDTO
     * @throws Exception 
     */
    public void salvar(UsuarioDTO usuarioDTO) throws Exception{
         if( Utils.isNullOrEmpty(usuarioDTO.getNome())
            || Utils.isNullOrEmpty(usuarioDTO.getSenha())
            || Utils.isNullOrEmpty(usuarioDTO.getEmail())
            || Utils.isNullOrEmpty(usuarioDTO.getUsuario())
            || Utils.isNullOrEmpty(usuarioDTO.getTipo())){
             throw new Exception("Dados inv�lidos!");
         }
         
         if(!usuarioDAO.salvar(usuarioDTO)){
            throw new Exception("Erro ao cadastrar!");
         }

     }
    /***
     * M�todo para <b>autenticar usu�rio</b>, utilizando login/usuario e senha.
     * Caso exista o usu�rio, o m�todo ir� retornar um objeto do tipo BaseDTO, onde o mesmo � 
     * herdado pelos objetos do DTO.
     * @param usuario
     * @param senha
     * @return BaseDTO (UsuarioDTO)
     * @throws Exception 
     */
    public BaseDTO autenticar(String usuario, String senha)throws Exception{
        Criterio criterio = new Criterio(new UsuarioDTO());
        criterio.setCriterio("login", " = '" + usuario.toLowerCase() + "'");
        criterio.setCriterio("senha", " = '" + senha.toLowerCase() + "'");
        List<BaseDTO> result = usuarioDAO.buscar(criterio);
        if(!Utils.isNullOrEmpty(result)){
            return result.get(0);
        }
        throw new Exception("Usu�rio ou senha inv�lidos!");
        
    }
}
