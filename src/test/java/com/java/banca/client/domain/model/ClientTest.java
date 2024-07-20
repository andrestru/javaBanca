package com.java.banca.client.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class ClientTest {
    @Test
    public void testClienteGettersAndSetters() {
        Client cliente = new Client();

        cliente.setId(1L);
        cliente.setName("Juan");
        cliente.setAddress("Calle");

        assertNotNull(cliente.getId());
        assertEquals(1L, cliente.getId());
        assertEquals("Juan", cliente.getName());
        assertEquals("Calle", cliente.getAddress());
    }
}
