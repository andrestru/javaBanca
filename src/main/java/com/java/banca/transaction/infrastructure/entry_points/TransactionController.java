package com.java.banca.transaction.infrastructure.entry_points;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.banca.transaction.domain.model.TransactionRequest;
import com.java.banca.transaction.domain.model.TransactionResponse;
import com.java.banca.transaction.domain.usecase.TransactionUseCase;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    private final TransactionUseCase transactionUseCase;

    @Autowired
    public TransactionController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionUseCase.createTransaction(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionUseCase.getTransactionById(id));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionUseCase.getTransactionsByAccountId(accountId));
    }
}
