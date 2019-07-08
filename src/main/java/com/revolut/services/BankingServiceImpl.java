package com.revolut.services;

import com.revolut.dtos.BankingRequest;
import com.revolut.model.Account;

import java.time.LocalDate;

public class BankingServiceImpl implements BankingService {

    private TransactionSystem transactionSystem;
    private AccountService accountService;

    public BankingServiceImpl() {
        transactionSystem = new TransactionSystemImpl();
        accountService = new AccountServiceImpl();
    }

    public static void main(String[] args) {
        Account account = new Account("123", "userid");
        BankingService bankingService = new BankingServiceImpl();
        account = ((BankingServiceImpl) bankingService).createAccount(account);
        ((BankingServiceImpl) bankingService).addMoney(new BankingRequest(account.getAccountId()
                , 12.2, LocalDate.now()));
        System.out.println(((BankingServiceImpl) bankingService).viewBalance(new BankingRequest(
                account.getAccountId())));
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(((BankingServiceImpl) bankingService).viewBalance(new BankingRequest(
                    account.getAccountId())));
        }
    }

    public void addMoney(BankingRequest bankingRequest) {
        transactionSystem.addMoneyIntoAccount(bankingRequest);
    }

    public double viewBalance(BankingRequest bankingRequest) {
        return accountService.getBalance(bankingRequest);
    }

    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }
}
