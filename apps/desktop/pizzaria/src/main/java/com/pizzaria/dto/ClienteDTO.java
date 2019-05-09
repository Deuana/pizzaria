/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;

import com.pizzaria.utilitarios.MapeamentoBancoCampo;
import com.pizzaria.utilitarios.MapeamentoBancoTabela;

/**
 *
 * @author Angelmário
 *  Classe criada para Cliente DTO, ou seja o objeto que irá "transitar" no código até sua inserção no Banco.
 */
@MapeamentoBancoTabela(nome = "Clientes")
public class ClienteDTO implements BaseDTO{
    @MapeamentoBancoCampo(nome = "id")
    private Integer id;
    @MapeamentoBancoCampo(nome = "nome")
    private String nome;
    @MapeamentoBancoCampo(nome = "telefone")
    private String telefone;
    @MapeamentoBancoCampo(nome = "endereco")
    private String endereco;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
