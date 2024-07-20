package com.java.banca.account.domain.model;

import lombok.Data;

@Data
public class AccountRequest {
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
}
