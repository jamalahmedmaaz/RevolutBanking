package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;

import java.time.LocalDateTime;

/**
 * The type Banking service.
 */
public class BankingServiceImpl implements BankingService {

    private static BankingService bankingService;

    private TransactionSystem transactionSystem;
    private AccountService accountService;

    private BankingServiceImpl() {
        transactionSystem = TransactionSystemImpl.getTransactionSystem();
        accountService = AccountServiceImpl.getAccountService();
    }

    /**
     * Gets banking service.
     *
     * @return the banking service
     */
    public static BankingService getBankingService() {
        if (bankingService == null) {
            synchronized (BankingServiceImpl.class) {
                bankingService = new BankingServiceImpl();
            }
        }
        return bankingService;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Account account = new Account("123", "userid");
        BankingServiceImpl bankingService = new BankingServiceImpl();
        account = bankingService.createAccount(account);
        bankingService.addMoney(new BankingRequestDTO(account.getAccountId()
                , 12.2, LocalDateTime.now()));
        System.out.println(bankingService.viewBalance(new BankingRequestDTO(
                account.getAccountId())));
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(bankingService.viewBalance(new BankingRequestDTO(
                    account.getAccountId())));
        }
    }

    @Override
    public String addMoney(BankingRequestDTO bankingRequestDTO) {
        return transactionSystem.addMoneyIntoAccount(bankingRequestDTO);
    }

    @Override
    public double viewBalance(BankingRequestDTO bankingRequestDTO) {
        return accountService.getBalance(bankingRequestDTO);
    }

    /**
     * Create account account.
     *
     * @param account the account
     * @return the account
     */
    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }
}
