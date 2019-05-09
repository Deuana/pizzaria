package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.persistencia.PedidoDAO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.utilitarios.Utils;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Angelmário
 */
public class PedidoRN {
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    /***
     * Método para salvar pedido, efetuando suas respectivas validações.
     * Caso falhe na validação ou no momento de salvar, o método sobe um Exception.
     * @param pedidoDTO 
     * @throws Exception 
     */
    public void salvar(PedidoDTO pedidoDTO) throws Exception{
         if( Utils.isNullOrEmpty(pedidoDTO.getFormaPagamento())
            || Utils.isNullOrEmpty(pedidoDTO.getProduto())
            || Utils.isNullOrEmpty(pedidoDTO.getCliente())
            || Utils.isNullOrEmpty(pedidoDTO.getValor())
            || BigDecimal.ZERO.equals(pedidoDTO.getValor())){
             throw new Exception("Dados inválidos!");
         }

          if(!pedidoDAO.salvar(pedidoDTO)){
           throw new Exception("Erro ao cadastrar!");             
        }
    }
    
    /***
     * Método para obter pedidos pendentes, efetuando suas respectivas validações.
     * Caso falhe na validação, o método sobe um Exception.
     * O método utiliza o objeto Criterio para fazer o filtro para a busca.
     * * O método retona uma lista de BaseDTO (PedidoDTO)
     * @return List</BaseDTO/>
     * @throws Exception 
     */
    public List<BaseDTO> obterPedidosPendentes() throws Exception{
        Criterio criterio = new Criterio(new PedidoDTO());
        criterio.setCriterio("pedidoPronto", " = 0 ");        
        criterio.setCriterio("pedidoFinalizado", " = 0 ");        
        List<BaseDTO> result = pedidoDAO.obterPedidos(criterio);
        if(!Utils.isNullOrEmpty(result)){
            return result;
        }
        throw new Exception("Não há pedidos!");

    }
    
    /***
     * Método para obter pedidos prontos, efetuando suas respectivas validações.
     * O método retona uma lista de BaseDTO (PedidoDTO)
     * @return List</BaseDTO/>
     * @throws Exception 
     */
    public List<BaseDTO> obterPedidosProntos() throws Exception{
        Criterio criterio = new Criterio(new PedidoDTO());
        criterio.setCriterio("pedidoPronto", " = 1 ");        
        criterio.setCriterio("pedidoFinalizado", " = 0 ");        
        List<BaseDTO> result = pedidoDAO.obterPedidos(criterio);
        if(!Utils.isNullOrEmpty(result)){
            return result;
        }
        throw new Exception("Não há pedidos!");

    }
    
    /***
     * Método para atualizar pedido, efetuando suas respectivas validações
     * @param pedidoDTO
     * @throws Exception 
     */
    public void atualizarPedido(PedidoDTO pedidoDTO) throws Exception{
        if(Utils.isNullOrEmpty(pedidoDTO)){
            throw new Exception("Selecione pelo meno um pedido!");
        }
        
        if(!pedidoDAO.atualizar(pedidoDTO)){
            throw  new Exception("Erro ao atualizar.");
        }
    }
}
