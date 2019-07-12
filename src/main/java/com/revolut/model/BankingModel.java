package com.revolut.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Banking model.
 */
public final class BankingModel {

    private static BankingModel bankingModel;
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private static Map<String, Transaction> ledger = new ConcurrentHashMap<>();

    private BankingModel() {
    }

    /**
     * Gets banking model.
     *
     * @return the banking model
     */
    public static BankingModel getBankingModel() {
        if (bankingModel == null) {
            synchronized (BankingModel.class) {
                bankingModel = new BankingModel();
            }
        }
        return bankingModel;
    }

    /**
     * Create account account.
     *
     * @param account the account
     * @return the account
     */
    public Account createAccount(Account account) {
        String uuid = UUID.randomUUID().toString();
        account.setAccountId(uuid);
        accounts.put(uuid, account);
        return account;
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    public User createUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        return users.put(uuid, user);
    }

    /**
     * Add amount into account.
     *
     * @param accountId       the account id
     * @param amount          the amount
     * @param transactionDate the transaction date
     */
    public void addAmountIntoAccount(String accountId, double amount,
                                     LocalDateTime transactionDate) {
        accounts.get(accountId).setAmount(accounts.get(accountId).getAmount() + amount);
        accounts.get(accountId).setLastTransactionDate(transactionDate);
    }

    /**
     * Reduce amount from account.
     *
     * @param accountId       the account id
     * @param amount          the amount
     * @param transactionTime the transaction time
     */
    public void reduceAmountFromAccount(String accountId, double amount,
                                        LocalDateTime transactionTime) {
        accounts.get(accountId).setAmount(accounts.get(accountId).getAmount() - amount);
        accounts.get(accountId).setLastTransactionDate(transactionTime);
    }

    /**
     * Gets account balance.
     *
     * @param fromAccountId the from account id
     * @return the account balance
     */
    public double getAccountBalance(String fromAccountId) {
        if (accounts.containsKey(fromAccountId)) {
            return accounts.get(fromAccountId).getAmount();
        }
        return 0.0;
    }

    /**
     * Update account account.
     *
     * @param account the account
     * @return the account
     */
    public Account updateAccount(Account account) {
        accounts.remove(account.getAccountId());
        return accounts.put(account.getAccountId(), account);
    }

    /**
     * Delete account boolean.
     *
     * @param accountId the account id
     * @return the boolean
     */
    public boolean deleteAccount(String accountId) {
        Account account = accounts.remove(accountId);
        return account != null;
    }

    /**
     * Does account exists boolean.
     *
     * @param accountId the account id
     * @return the boolean
     */
    public boolean doesAccountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    /**
     * Add transaction.
     *
     * @param transaction the transaction
     */
    public void addTransaction(Transaction transaction) {
        ledger.put(transaction.getTransactionId(), transaction);
    }

    /**
     * Update transaction.
     *
     * @param transaction the transaction
     */
    public void updateTransaction(Transaction transaction) {
        if (ledger.containsKey(transaction.getTransactionId())) {
            ledger.remove(transaction.getTransactionId(), transaction);
            ledger.put(transaction.getTransactionId(), transaction);
        }
    }

    /**
     * Mark transaction completed.
     *
     * @param transaction the transaction
     */
    public void markTransactionCompleted(Transaction transaction) {
        if (ledger.containsKey(transaction.getTransactionId())) {
            ledger.put(transaction.getTransactionId(), transaction);
        }
    }

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    public User updateUser(User user) {
        users.remove(user.getUserId());
        users.put(user.getUserId(), user);
        return users.get(user.getUserId());
    }

    /**
     * Gets transaction status.
     *
     * @param transactionId the transaction id
     * @return the transaction status
     */
    public TransactionStatus getTransactionStatus(String transactionId) {
        return ledger.get(transactionId).getTransactionStatus();
    }

    /**
     * Gets transaction.
     *
     * @param transactionId the transaction id
     * @return the transaction
     */
    public Transaction getTransaction(String transactionId) {
        return ledger.get(transactionId);
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     */
    public void deleteUser(String userId) {
        if (users.containsKey(userId)) {
            List<String> accountIds = users.get(userId).getAccounts();
            accounts.keySet().removeAll(accountIds);
        }
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    public User getUser(String userId) {
        return users.get(userId);
    }

    /**
     * Gets account.
     *
     * @param accountId the account id
     * @return the account
     */
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    /**
     * Block account.
     *
     * @param accountId the account id
     */
    public void blockAccount(String accountId) {
        accounts.get(accountId).setAccountStatus(AccountStatus.BLOCKED);
    }

    /**
     * Activate account.
     *
     * @param accountId the account id
     */
    public void activateAccount(String accountId) {
        accounts.get(accountId).setAccountStatus(AccountStatus.ACTIVE);
    }
}
