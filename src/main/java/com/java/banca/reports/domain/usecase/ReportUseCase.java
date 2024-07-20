package com.java.banca.reports.domain.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.banca.account.domain.model.Account;
import com.java.banca.account.domain.model.repository.AccountRepository;
import com.java.banca.application.BusinessException;
import com.java.banca.client.domain.model.Client;
import com.java.banca.client.domain.model.repository.ClientRepository;
import com.java.banca.reports.domain.ReportsRequest;
import com.java.banca.reports.domain.ReportsResponse;
import com.java.banca.transaction.domain.model.Transaction;
import com.java.banca.transaction.domain.model.repository.TransactionRepository;

@Service
public class ReportUseCase {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportUseCase(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public ReportsResponse generateReport(ReportsRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new BusinessException("Cliente no encontrado", "400-09"));

        List<Account> accounts = accountRepository.findAll().stream()
                .filter(account -> account.getId().equals(client.getId()))
                .collect(Collectors.toList());

        List<ReportsResponse.AccountResponse> accountsResponse = accounts.stream()
                .map(account -> {
                    List<Transaction> transactions = transactionRepository.findByAccountIdAndDateBetween(
                        account.getId(), 
                        request.getStartDate().atStartOfDay(), 
                        request.getEndDate().atStartOfDay()
                        );
                        List<ReportsResponse.TransactionResponse> transactionsResponse = transactions.stream()
                        .map(transactional -> {
                            ReportsResponse.TransactionResponse transactionResponse = new ReportsResponse.TransactionResponse();
                            transactionResponse.setAmount(transactional.getAmount());
                            transactionResponse.setDate(transactional.getDate().toLocalDate());
                            transactionResponse.setId(transactional.getId());
                            return transactionResponse;
                        }).collect(Collectors.toList());
                        ReportsResponse.AccountResponse accountResponse = new ReportsResponse.AccountResponse();
                        accountResponse.setAccountNumber(account.getAccountNumber());
                        accountResponse.setAccountType(account.getAccountType());
                        accountResponse.setBalance(account.getInitialBalance());
                        accountResponse.setTransactions(transactionsResponse);
                        return accountResponse;
                    }).collect(Collectors.toList());

        ReportsResponse response = new ReportsResponse();
        response.setClientId(client.getId());
        response.setAccounts(accountsResponse);
        return response;
    }
}
