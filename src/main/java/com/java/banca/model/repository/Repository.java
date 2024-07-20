package com.java.banca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.banca.model.Person;

public interface Repository extends JpaRepository<Person, Long>{

    
} 
