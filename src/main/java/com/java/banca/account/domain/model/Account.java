package com.java.banca.account.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_number")
    private String accountNumber;
    
    @Column(name = "account_type")
    private String accountType;
    
    @Column(name = "initial_balance")
    private Double initialBalance;
    
    private String status;

}
