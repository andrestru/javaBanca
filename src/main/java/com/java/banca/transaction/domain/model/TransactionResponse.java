package com.java.banca.transaction.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionResponse {
    private Long id;
    private Long accountId;
    private Double amount;
    private LocalDateTime date;
}
