package com.java.banca.account.domain.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.banca.account.domain.model.Account;
import com.java.banca.account.domain.model.AccountRequest;
import com.java.banca.account.domain.model.AccountResponse;
import com.java.banca.account.domain.model.repository.AccountRepository;
import com.java.banca.application.BusinessException;

@Service
public class AccountUseCase {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(AccountRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setInitialBalance(request.getInitialBalance());
        account.setStatus(request.getStatus());
        accountRepository.save(account);

        return toResponse(account);
    }

    public AccountResponse updateAccount(Long id, AccountRequest request) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Cuenta no encontrada", "400-01"));
    
        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setInitialBalance(request.getInitialBalance());
        account.setStatus(request.getStatus());
        accountRepository.save(account);
    
        return toResponse(account);
    }
    
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new BusinessException("Cuenta no encontrada", "400-02");
        }
        accountRepository.deleteById(id);
    }
    
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Cuenta no encontrada", "400-03"));
        return toResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setInitialBalance(account.getInitialBalance());
        response.setStatus(account.getStatus());
        return response;
    }
}