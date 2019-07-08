package com.revolut.model;
import com.revolut.exceptions.BankingException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BankingModel {

    private static BankingModel bankingModel;
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    private BankingModel() {
    }

    public static BankingModel getBankingModel() {
        if (bankingModel == null) {
            synchronized (BankingModel.class) {
                bankingModel = new BankingModel();
            }
        }
        return bankingModel;
    }

    public Account createAccount(Account account) {
        if (accounts.containsKey(account.getAccountId())) {
            throw new BankingException();
        } else {
            String uuid = UUID.randomUUID().toString();
            account.setAccountId(uuid);
            accounts.put(uuid, account);
        }
        return account;
    }

    public User createUser(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new BankingException();
        } else {
            String uuid = UUID.randomUUID().toString();
            user.setUserId(uuid);
            users.put(uuid, user);
        }
        return user;
    }

    public void addAmountIntoAccount(String accountId, double amount,
                                     LocalDate transactionDate) {
        if (accounts.containsKey(accountId)) {
            accounts.get(accountId).setAmount(accounts.get(accountId).getAmount() + amount);
            accounts.get(accountId).setLastTransactionDate(transactionDate);
        }
    }

    public void reduceAmountFromAccount(String accountId, double amount,
                                        LocalDate transactionTime) {
        if (accounts.containsKey(accountId)) {
            accounts.get(accountId).setAmount(accounts.get(accountId).getAmount() - amount);
            accounts.get(accountId).setLastTransactionDate(transactionTime);
        }
    }

    public double getAccountBalance(String fromAccountId) {
        if (accounts.containsKey(fromAccountId)) {
            return accounts.get(fromAccountId).getAmount();
        }
        return 0;
    }
}
