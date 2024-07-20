package com.java.banca.client.infrastructure.entry_points;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.banca.client.domain.model.ClientRequest;
import com.java.banca.client.domain.model.ClientResponse;
import com.java.banca.client.domain.usecase.ClientUseCase;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientUseCase clientUseCase;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ClientController(clientUseCase))
                .build();
    }

    @Test
    public void testCreateClient() throws Exception {
        // Crear un objeto ClientRequest
        ClientRequest request = new ClientRequest();
        request.setName("Andres");
        request.setGender("Masculino");
        request.setAge(23);
        request.setIdentification("10065");
        request.setAddress("Bello");
        request.setPhone("322947");
        request.setClientId("15979");
        request.setPassword("And123");
        request.setStatus("ACTIVE");

        // Simular la respuesta del use case
        ClientResponse response = new ClientResponse();
        response.setId(1L);
        response.setClientId("15979");
        response.setName("Andres");
        response.setGender("Masculino");
        response.setAge(23);
        response.setIdentification("10065");
        response.setAddress("Bello");
        response.setPhone("322947");
        response.setStatus("ACTIVE");

        when(clientUseCase.createClient(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Andres\",\n" +
                                "    \"gender\": \"Masculino\",\n" +
                                "    \"age\": 23,\n" +
                                "    \"identification\": \"10065\",\n" +
                                "    \"address\": \"Bello\",\n" +
                                "    \"phone\": \"322947\",\n" +
                                "    \"clientId\": \"15979\",\n" +
                                "    \"password\": \"And123\",\n" +
                                "    \"status\": \"ACTIVE\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value("15979"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Andres"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Masculino"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identification").value("10065"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Bello"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("322947"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACTIVE"));
    }
}

