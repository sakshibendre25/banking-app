package com.sakshi.banking_app.service;

import com.sakshi.banking_app.dto.AccountDTO;

import java.util.List;


public interface AccountService {

    AccountDTO createAccount( AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO deposit(Long id, double amount);

    AccountDTO withdraw(Long id, double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long id);
}
