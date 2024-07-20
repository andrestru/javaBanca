package com.java.banca.account.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.banca.account.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}