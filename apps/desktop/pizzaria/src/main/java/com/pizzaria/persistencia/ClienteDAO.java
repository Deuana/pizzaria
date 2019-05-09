/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.persistencia;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.Criterio;
import java.util.List;

/**
 *
 * @author Angelmário
 * Classe criada para inserção e busca do Cliente no Banco.
 */
public class ClienteDAO extends BaseDAO{

    @Override
    public List<BaseDTO> buscar(Criterio c) {
        return super.buscar(c); 
    }


    @Override
    public boolean salvar(BaseDTO modelo) {
        return super.salvar(modelo);
    }
    
    
    
}
