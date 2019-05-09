package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.TipoUsuarioDTO;
import com.pizzaria.persistencia.TipoUsuarioDAO;
import com.pizzaria.dto.Criterio;
import java.util.List;


/**
 *
 * @author Angelm�rio
 */
public class TipoUsuarioRN {
    
    TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
    /***
     * M�todo para trazer os tipos de usu�rios do sistema.
     * O m�todo apenas informa de qual inst�ncia de objeto que a busca ser� feita.
     * O m�todo retorna uma lista de BaseDTO (TiposUsuariosDTO)
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> obterTiposUsuarios(){  
        Criterio criterio = new Criterio(new TipoUsuarioDTO());
         return tipoUsuarioDAO.buscar(criterio);
                 
         
     }
}
