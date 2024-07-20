package com.java.banca.client.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.banca.client.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
