/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;

import com.pizzaria.utilitarios.MapeamentoBancoCampo;
import com.pizzaria.utilitarios.MapeamentoBancoTabela;
import java.math.BigDecimal;


/**
 *
 * @author Angelmário
 *  Classe criada para Produto DTO, ou seja o objeto Produto que irá "transitar" no código até sua inserção no Banco.
 */
@MapeamentoBancoTabela(nome = "Produtos")
public class ProdutoDTO implements BaseDTO{
    
    @MapeamentoBancoCampo(nome = "id")
    private Integer id;
    @MapeamentoBancoCampo(nome = "descricao")
    private String descricao;
    @MapeamentoBancoCampo(nome = "valor")
    private BigDecimal valor;
    @MapeamentoBancoCampo(nome = "quantidade")
    private Integer quantidade;

    /**
     * @return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
