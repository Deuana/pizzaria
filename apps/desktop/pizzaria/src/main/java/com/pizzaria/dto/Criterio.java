/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.dto;

import java.util.HashMap;

/**
 * Classe criada para ser utilizada no método de busca.
 * 
 * @author Angelmário
 */
public class Criterio {
    private HashMap<String, String> criterios = new HashMap<String, String>();
    private BaseDTO instanciaObjetoBusca;

    
    public Criterio(BaseDTO objeto){
        this.instanciaObjetoBusca = objeto;
    }
    /**
     * @return the criterios
     */
    public HashMap<String, String> getCriterios() {
        return criterios;
    }

    /**
     * @param campo
     * @param parametro
     */
    public void setCriterio(String campo, String parametro) {
        this.criterios.put(campo, parametro);
    }

    /**
     * @return the instanciaObjetoBusca
     */
    public BaseDTO getObjetoBusca() {
        return instanciaObjetoBusca;
    }

    /**
     * @param instanciaObjetoBusca the objetoBusca to set
     */
    public void setObjetoBusca(BaseDTO instanciaObjetoBusca) {
        this.instanciaObjetoBusca = instanciaObjetoBusca;
    }
}
