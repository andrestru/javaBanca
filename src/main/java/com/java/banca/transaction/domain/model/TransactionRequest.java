package com.java.banca.transaction.domain.model;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long accountId;
    private Double amount;
}
