
package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.dto.TipoUsuarioDTO;
import com.pizzaria.persistencia.ClienteDAO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.utilitarios.Utils;
import java.util.List;

/**
 *
 * @author Deuana
 */
public class ClienteRN {
     
    ClienteDAO clienteDAO= new ClienteDAO();
    /***
     * Método para salvar cliente e efetuando suas respectivas validações.
     * Caso falhe na validação ou no momento de salvar, o método sobe um Exception.
     * @param cliente
     * @throws Exception 
     */
    public void salvar(ClienteDTO cliente) throws Exception{
        this.verificarClienteCadastrado(cliente.getTelefone());
        if(Utils.isNullOrEmpty(cliente.getNome())|| 
           Utils.isNullOrEmpty(cliente.getTelefone())||
           Utils.isNullOrEmpty(cliente.getEndereco())){
           
            throw new Exception("Dados inválidos!");
        }
        if(!clienteDAO.salvar(cliente)){
           throw new Exception("Erro ao cadastrar!");             
        }
        
    }
    /***
     * Método para obter clientes.
     * O método retorna uma lista de BaseDTO (ClienteDTO)
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> obterClientes(){       
         Criterio criterio = new Criterio(new ClienteDTO());
         return clienteDAO.buscar(criterio);
         
    }
  
    /***
     * Método para verificar se o cliente está cadastrado no sistema, efetuando suas respectivas validações.
     * O método utiliza o telefone para verificar se existe o cliente no sistema. 
     * Caso não exista ou não passe na validação, o método sobe um Exception.
     * @param telefone
     * @throws Exception 
     */
    public void verificarClienteCadastrado(String telefone) throws Exception{
      if(Utils.isNullOrEmpty(telefone)){                     
            throw new Exception("Insira o número de telefone!");
        }
        Criterio c = new Criterio(new ClienteDTO());
        c.setObjetoBusca(new ClienteDTO());
        c.setCriterio("telefone", " = '" + telefone + "'");
        
        if(!Utils.isNullOrEmpty(clienteDAO.buscar(c))){
            throw new Exception("O cliente já está cadastrado!");
        } 
    }
}