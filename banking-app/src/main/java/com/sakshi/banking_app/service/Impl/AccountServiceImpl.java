package com.sakshi.banking_app.service.Impl;

import com.sakshi.banking_app.dto.AccountDTO;
import com.sakshi.banking_app.entity.Account;
import com.sakshi.banking_app.mapper.AccountMapper;
import com.sakshi.banking_app.repository.AccountRepository;
import com.sakshi.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
    Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Account does not exists"));

        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not not exists"));

     double total = account.getBalance() + amount;
     account.setBalance(total);
     Account savedAccount = accountRepository.save(account);
     return AccountMapper.mapToAccountDTO(savedAccount);


    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {

      List<Account> accounts =   accountRepository.findAll();
     return accounts.stream().map((account) -> AccountMapper.mapToAccountDTO(account))
              .collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);






    }
}
