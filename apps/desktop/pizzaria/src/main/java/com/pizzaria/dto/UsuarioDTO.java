package com.pizzaria.dto;

import com.pizzaria.utilitarios.*;
/**
 *  Classe criada para Usuário DTO, ou seja o objeto que irá "transitar" no código até sua inserção no Banco.
 * O objeto usuário é relacionado a pessoa em si, ou seja, o nome, o login, e-mail.
 */
@MapeamentoBancoTabela(nome = "Usuarios")
public class UsuarioDTO implements BaseDTO{
    
    @MapeamentoBancoCampo(nome = "id")
    private Integer id;

    @MapeamentoBancoCampo(nome = "nome")
    private String nome;

    @MapeamentoBancoCampo(nome = "senha")
    private String senha;

    @MapeamentoBancoCampo(nome = "email")
    private  String email;
    
    @MapeamentoBancoCampo(nome = "login")
    private  String usuario;
    
    @MapeamentoBancoCampo(nome = "idTipoUsuario")
    private TipoUsuarioDTO tipo;

    @Override
    public Integer getId() {
        return id;
    }

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
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the tipo
     */
    public TipoUsuarioDTO getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoUsuarioDTO tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}