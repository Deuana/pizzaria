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
 */
public class ProdutoDAO extends BaseDAO{
/**
 * Método para obter produtos cadastrados no banco de dados.
 */
    @Override
    public List<BaseDTO> buscar(Criterio c) {
        return super.buscar(c);
    }
  
}
