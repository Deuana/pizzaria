/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;


import com.pizzaria.utilitarios.MapeamentoBancoCampo;
import com.pizzaria.utilitarios.MapeamentoBancoTabela;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 *
 * @author Angelmário
 *  Classe criada para Pedido DTO, ou seja o objeto Pedido que irá "transitar" no código até sua inserção no Banco.
 */
@MapeamentoBancoTabela(nome = "Pedidos")
public class PedidoDTO implements BaseDTO{

    @MapeamentoBancoCampo(nome = "id")
    private Integer id;
    @MapeamentoBancoCampo(nome = "idCliente")
    private ClienteDTO cliente;
    
    private List<ProdutoDTO> produto;
    @MapeamentoBancoCampo(nome = "formaPagamento")
    private String formaPagamento;
    @MapeamentoBancoCampo(nome = "valor")
    private BigDecimal valor;
    @MapeamentoBancoCampo(nome = "pedidoFinalizado")
    private Boolean pedidoFinalizado;
    @MapeamentoBancoCampo(nome = "pedidoPronto")
    private Boolean pedidoPronto;
    @MapeamentoBancoCampo(nome = "timeInsert")
    private Timestamp timeInsert;
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
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the produto
     */
    public List<ProdutoDTO> getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(List<ProdutoDTO> produto) {
        this.produto = produto;
    }

    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPedido to set
     */
    public void setFormaPagamento(String formaPagamento){
        this.formaPagamento = formaPagamento;
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
     * @return the pedidoFinalizado
     */
    public Boolean isPedidoFinalizado() {
        return pedidoFinalizado;
    }

    /**
     * @param pedidoFinalizado the pedidoFinalizado to set
     */
    public void setPedidoFinalizado(Boolean pedidoFinalizado) {
        this.pedidoFinalizado = pedidoFinalizado;
    }

    /**
     * @return the pedidoPronto
     */
    public Boolean isPedidoPronto() {
        return pedidoPronto;
    }

    /**
     * @param pedidoPronto the pedidoPronto to set
     */
    public void setPedidoPronto(Boolean pedidoPronto) {
        this.pedidoPronto = pedidoPronto;
    }

    /**
     * @return the timeInsert
     */
    public Timestamp getTimeInsert() {
        return timeInsert;
    }

    /**
     * @param timeInsert the timeInsert to set
     */
    public void setTimeInsert(Timestamp timeInsert) {
        this.timeInsert = timeInsert;
    }
}
