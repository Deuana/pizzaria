
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
     * M�todo para salvar cliente e efetuando suas respectivas valida��es.
     * Caso falhe na valida��o ou no momento de salvar, o m�todo sobe um Exception.
     * @param cliente
     * @throws Exception 
     */
    public void salvar(ClienteDTO cliente) throws Exception{
        this.verificarClienteCadastrado(cliente.getTelefone());
        if(Utils.isNullOrEmpty(cliente.getNome())|| 
           Utils.isNullOrEmpty(cliente.getTelefone())||
           Utils.isNullOrEmpty(cliente.getEndereco())){
           
            throw new Exception("Dados inv�lidos!");
        }
        if(!clienteDAO.salvar(cliente)){
           throw new Exception("Erro ao cadastrar!");             
        }
        
    }
    /***
     * M�todo para obter clientes.
     * O m�todo retorna uma lista de BaseDTO (ClienteDTO)
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> obterClientes(){       
         Criterio criterio = new Criterio(new ClienteDTO());
         return clienteDAO.buscar(criterio);
         
    }
  
    /***
     * M�todo para verificar se o cliente est� cadastrado no sistema, efetuando suas respectivas valida��es.
     * O m�todo utiliza o telefone para verificar se existe o cliente no sistema. 
     * Caso n�o exista ou n�o passe na valida��o, o m�todo sobe um Exception.
     * @param telefone
     * @throws Exception 
     */
    public void verificarClienteCadastrado(String telefone) throws Exception{
      if(Utils.isNullOrEmpty(telefone)){                     
            throw new Exception("Insira o n�mero de telefone!");
        }
        Criterio c = new Criterio(new ClienteDTO());
        c.setObjetoBusca(new ClienteDTO());
        c.setCriterio("telefone", " = '" + telefone + "'");
        
        if(!Utils.isNullOrEmpty(clienteDAO.buscar(c))){
            throw new Exception("O cliente j� est� cadastrado!");
        } 
    }
}