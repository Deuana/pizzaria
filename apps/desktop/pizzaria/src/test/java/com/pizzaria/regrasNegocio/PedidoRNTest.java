package com.pizzaria.regrasNegocio;

import com.pizzaria.dto.PedidoDTO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Deuana
 */
public class PedidoRNTest {
    
    public PedidoRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of salvar method, of class PedidoRN.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testValidarDadosEmBrancoPedido() throws Exception {
        System.out.println("salvar");
        PedidoDTO pedidoDTO = new PedidoDTO();
        PedidoRN instance = new PedidoRN();
        instance.salvar(pedidoDTO);
    }
    
}
