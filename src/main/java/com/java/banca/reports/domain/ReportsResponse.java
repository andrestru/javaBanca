package com.java.banca.reports.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ReportsResponse {
    private Long clientId;
    private List<AccountResponse> accounts;


    @Data
    public static class AccountResponse {
        private String accountNumber;
        private String accountType;
        private Double balance;
        private List<TransactionResponse> transactions;

    }

    @Data
    public static class TransactionResponse {
        private Long id;
        private Double amount;
        private LocalDate date;

    }
}
