package com.java.banca.transaction.domain.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.banca.account.domain.model.Account;
import com.java.banca.account.domain.model.repository.AccountRepository;
import com.java.banca.application.BusinessException;
import com.java.banca.transaction.domain.model.Transaction;
import com.java.banca.transaction.domain.model.TransactionRequest;
import com.java.banca.transaction.domain.model.TransactionResponse;
import com.java.banca.transaction.domain.model.repository.TransactionRepository;

@Service
public class TransactionUseCase {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new BusinessException("Transaccion no encontrada", "400-06"));

        Double newBalance = account.getInitialBalance() + request.getAmount();
        if (newBalance < 0) {
            throw new BusinessException("Transaccion no encontrada", "400-07");
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(request.getAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        return toResponse(transaction);
    }

    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Transaccion no encontrada", "400-08"));
        return toResponse(transaction);
    }

    public List<TransactionResponse> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse toResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setAccountId(transaction.getAccountId());
        response.setAmount(transaction.getAmount());
        response.setDate(transaction.getDate());
        return response;
    }
}
