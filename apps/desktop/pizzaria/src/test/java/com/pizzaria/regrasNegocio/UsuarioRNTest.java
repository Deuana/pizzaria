/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.UsuarioDTO;
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
public class UsuarioRNTest {
    
    public UsuarioRNTest() {
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
     * Test of salvar method, of class UsuarioRN.
     */
    @Test(expected = Exception.class)
    public void testValidarDadosEmBrancoUsuario() throws Exception {
        System.out.println("salvar");
        UsuarioDTO usuarioDTO = null;
        UsuarioRN instance = new UsuarioRN();
        instance.salvar(usuarioDTO);
    }

    /**
     * Test of autenticar method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testAutenticar() throws Exception {
        System.out.println("autenticar");
        String usuario = "XXXX";
        String senha = "XXXXX";
        UsuarioRN instance = new UsuarioRN();
        instance.autenticar(usuario, senha);      
    }
    
}
