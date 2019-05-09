/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.ProdutoDTO;
import com.pizzaria.persistencia.ProdutoDAO;
import com.pizzaria.dto.Criterio;
import com.pizzaria.utilitarios.Utils;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Angelmário
 */
public class ProdutoRN {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    /***
     * Método para obter produtos.
     * O método utiliza o objeto criterio para efetuar a busca.
     * O método retona uma lista de BaseDTO (ProdutoDTO)
     * @return List</BaseDTO/>
     */
    public List<BaseDTO> obterProdutos(){         
        Criterio criterio = new Criterio(new ProdutoDTO());
        return produtoDAO.buscar(criterio);
         
     }
    
    /***
     * Método para salvar produto, efetuando suas respectivas validações.
     * Caso falhe na validação ou no momento de salvar, o método sobe um Exception.
     * @param produtoDTO
     * @throws Exception 
     */
    public void salvar(ProdutoDTO produtoDTO) throws Exception{
         if( Utils.isNullOrEmpty(produtoDTO.getDescricao())
            || Utils.isNullOrEmpty(produtoDTO.getValor())
            || BigDecimal.ZERO.equals(produtoDTO.getValor())){
             throw new Exception("Dados inválidos!");
         }
         
         if(!produtoDAO.salvar(produtoDTO)){
            throw new Exception("Erro ao cadastrar!");
         }
    }
}
