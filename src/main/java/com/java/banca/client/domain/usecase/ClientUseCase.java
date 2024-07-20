package com.java.banca.client.domain.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.banca.application.BusinessException;
import com.java.banca.client.domain.model.Client;
import com.java.banca.client.domain.model.ClientRequest;
import com.java.banca.client.domain.model.ClientResponse;
import com.java.banca.client.domain.model.repository.ClientRepository;

@Service
public class ClientUseCase {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponse createClient(ClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setGender(request.getGender());
        client.setAge(request.getAge());
        client.setIdentification(request.getIdentification());
        client.setAddress(request.getAddress());
        client.setPhone(request.getPhone());
        client.setClientId(request.getClientId()); 
        client.setPassword(request.getPassword());
        client.setStatus(request.getStatus());
        clientRepository.save(client);
    
        return toResponse(client);
    }
    
    public ClientResponse updateClient(Long id, ClientRequest request) {
        Client client = clientRepository.findById(id).orElseThrow(() -> 
        new BusinessException("Cliente no encontrada", "400-04"));
        client.setName(request.getName());
        client.setGender(request.getGender());
        client.setAge(request.getAge());
        client.setIdentification(request.getIdentification());
        client.setAddress(request.getAddress());
        client.setPhone(request.getPhone());
        client.setClientId(request.getClientId());
        client.setPassword(request.getPassword());
        client.setStatus(request.getStatus());
        clientRepository.save(client);

        return toResponse(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> 
        new BusinessException("Cliente no encontrada", "400-05"));
        return toResponse(client);
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ClientResponse toResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setGender(client.getGender());
        response.setAge(client.getAge());
        response.setIdentification(client.getIdentification());
        response.setAddress(client.getAddress());
        response.setPhone(client.getPhone());
        response.setClientId(client.getClientId());
        response.setStatus(client.getStatus());
        return response;
    }
}
