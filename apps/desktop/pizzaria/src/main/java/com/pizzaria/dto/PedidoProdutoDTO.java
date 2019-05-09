/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;

import com.pizzaria.utilitarios.MapeamentoBancoCampo;
import com.pizzaria.utilitarios.MapeamentoBancoTabela;
import java.sql.Timestamp;

/**
 *
 * @author Angelmário 
 * Classe criada para relacionamento dos produtos e pedidos.
 */
@MapeamentoBancoTabela(nome = "PedidoProduto")
public class PedidoProdutoDTO implements BaseDTO{
    
    @MapeamentoBancoCampo(nome = "id")
    private Integer id;
    @MapeamentoBancoCampo(nome = "idPedido")
    private Integer idPedido;
    @MapeamentoBancoCampo(nome = "idProduto")
    private Integer idProduto;
    @MapeamentoBancoCampo(nome = "horapedido")
    private Timestamp horapedido;
    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idPedido
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the horapedido
     */
    public Timestamp getHorapedido() {
        return horapedido;
    }

    /**
     * @param horapedido the horapedido to set
     */
    public void setHorapedido(Timestamp horapedido) {
        this.horapedido = horapedido;
    }
    
}
