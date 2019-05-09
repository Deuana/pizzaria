/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;

/**
 * Classe base para a implementação do acesso ao banco de dados.
 * 
 * @author Adiel
 */
public interface BaseDTO {
    
    /**
     * 
     * @return ID do registro no banco.
     */
    Integer getId();
    
    /**
     * Seta o ID do registro.
     * @param id 
     */
    void setId(Integer id);
}
