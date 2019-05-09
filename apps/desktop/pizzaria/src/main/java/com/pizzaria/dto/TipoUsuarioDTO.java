
package com.pizzaria.dto;

import com.pizzaria.utilitarios.MapeamentoBancoCampo;
import com.pizzaria.utilitarios.MapeamentoBancoTabela;

/**
 *  Classe criada para Tipo do usuário DTO, ou seja o objeto que irá "transitar" no código até sua inserção no Banco.
 * O tipo é relacionado ao funcionário, ou seja, seu cargo e, desta forma, as funções que terá acesso no sistema.
 */
@MapeamentoBancoTabela(nome = "TipoUsuario")
public class TipoUsuarioDTO  implements BaseDTO{
    @MapeamentoBancoCampo(nome = "id")
    private Integer id;
    
    @MapeamentoBancoCampo(nome = "descricao")
    private String descricao;
    
    @Override
    public Integer getId() {
        return id;
    }

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
    
}
