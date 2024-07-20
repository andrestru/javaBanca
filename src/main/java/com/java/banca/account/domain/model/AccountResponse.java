package com.java.banca.account.domain.model;

import lombok.Data;

@Data
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;

}