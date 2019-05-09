/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
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
public class TipoUsuarioRNTest {
    
    public TipoUsuarioRNTest() {
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
     * Test of obterTiposUsuarios method, of class TipoUsuarioRN.
     */
    @Test
    public void testExisteTiposUsuarios() {
        System.out.println("obterTiposUsuarios");
        TipoUsuarioRN instance = new TipoUsuarioRN();
        boolean expResult = false;        
        assertEquals(expResult, Utils.isNullOrEmpty(instance.obterTiposUsuarios()));
    }
    
}
