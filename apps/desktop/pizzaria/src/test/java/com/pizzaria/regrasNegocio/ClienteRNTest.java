package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.utilitarios.Utils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Deuana
 */
public class ClienteRNTest {
    
    public ClienteRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    } 
    /**
     * Test of cadastrarCliente method, of class ClienteRN.
     * @throws java.lang.Exception
     */
    @Test(expected=Exception.class)
    public void testValidarDadosEmBrancoCliente() throws Exception {
        System.out.println("cadastrarCliente");
        ClienteDTO cliente = new ClienteDTO();
        ClienteRN instance = new ClienteRN();
        instance.salvar(cliente);
    }

    /**
     * Test of obterClientes method, of class ClienteRN.
     */
    @Test
    public void testExisteClientes() {
        System.out.println("obterClientes");
        ClienteRN instance = new ClienteRN();
        boolean expResult = false;        
        assertEquals(expResult, Utils.isNullOrEmpty(instance.obterClientes()));
    }

    @Test
    public void testValidarClienteNaoCadastrado() throws Exception {
        System.out.println("verificarClienteCadastrado");
        String telefone = "XXXXXXXX";
        ClienteRN instance = new ClienteRN();
        instance.verificarClienteCadastrado(telefone);
    }
    
}
