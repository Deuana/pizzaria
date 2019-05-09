package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.TipoUsuarioDTO;
import com.pizzaria.persistencia.TipoUsuarioDAO;
import com.pizzaria.dto.Criterio;
import java.util.List;


/**
 *
 * @author Angelmário
 */
public class TipoUsuarioRN {
    
    TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
    /***
     * Método para trazer os tipos de usuários do sistema.
     * O método apenas informa de qual instância de objeto que a busca será feita.
     * O método retorna uma lista de BaseDTO (TiposUsuariosDTO)
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> obterTiposUsuarios(){  
        Criterio criterio = new Criterio(new TipoUsuarioDTO());
         return tipoUsuarioDAO.buscar(criterio);
                 
         
     }
}
