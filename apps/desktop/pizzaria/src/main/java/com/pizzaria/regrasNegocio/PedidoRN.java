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
 * @author Angelm�rio
 */
public class PedidoRN {
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    /***
     * M�todo para salvar pedido, efetuando suas respectivas valida��es.
     * Caso falhe na valida��o ou no momento de salvar, o m�todo sobe um Exception.
     * @param pedidoDTO 
     * @throws Exception 
     */
    public void salvar(PedidoDTO pedidoDTO) throws Exception{
         if( Utils.isNullOrEmpty(pedidoDTO.getFormaPagamento())
            || Utils.isNullOrEmpty(pedidoDTO.getProduto())
            || Utils.isNullOrEmpty(pedidoDTO.getCliente())
            || Utils.isNullOrEmpty(pedidoDTO.getValor())
            || BigDecimal.ZERO.equals(pedidoDTO.getValor())){
             throw new Exception("Dados inv�lidos!");
         }

          if(!pedidoDAO.salvar(pedidoDTO)){
           throw new Exception("Erro ao cadastrar!");             
        }
    }
    
    /***
     * M�todo para obter pedidos pendentes, efetuando suas respectivas valida��es.
     * Caso falhe na valida��o, o m�todo sobe um Exception.
     * O m�todo utiliza o objeto Criterio para fazer o filtro para a busca.
     * * O m�todo retona uma lista de BaseDTO (PedidoDTO)
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
        throw new Exception("N�o h� pedidos!");

    }
    
    /***
     * M�todo para obter pedidos prontos, efetuando suas respectivas valida��es.
     * O m�todo retona uma lista de BaseDTO (PedidoDTO)
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
        throw new Exception("N�o h� pedidos!");

    }
    
    /***
     * M�todo para atualizar pedido, efetuando suas respectivas valida��es
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
