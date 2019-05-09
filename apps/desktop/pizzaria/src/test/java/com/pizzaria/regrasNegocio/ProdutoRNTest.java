/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.ProdutoDTO;
import com.pizzaria.utilitarios.Utils;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angelmário
 */
public class ProdutoRNTest {
    
    public ProdutoRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of obterProdutos method, of class ProdutoRN.
     */
    @Test
    public void testExisteProduto() {
        System.out.println("obterProdutos");
        ProdutoRN instance = new ProdutoRN();
        boolean expResult = false;        
        assertEquals(expResult, Utils.isNullOrEmpty(instance.obterProdutos()));
    }

    /**
     * Test of salvar method, of class ProdutoRN.
     */
    @Test(expected = Exception.class)
    public void testValidarDadosEmBrancoProduto() throws Exception {
        System.out.println("salvar");
        ProdutoDTO produtoDTO = null;
        ProdutoRN instance = new ProdutoRN();
        instance.salvar(produtoDTO);
    }
    
}
