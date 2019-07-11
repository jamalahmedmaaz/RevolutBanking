package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;
import com.revolut.model.TransactionStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;
    private BankingService bankingService;
    @Before
    public void setUp() throws Exception {
        accountService = AccountServiceImpl.getAccountService();
        bankingService = BankingServiceImpl.getBankingService();
    }

    @After
    public void tearDown() throws Exception {
        accountService = null;
    }

    @Test
    public void createAccount() {
        Account account = accountService.createAccount(createAccountRequest());
        assertNotNull("Account cannnot be null", account);
    }

    @Test
    public void getBalance() {
        Account account = accountService.createAccount(createAccountRequest());
        assertNotNull("Account can not be null", account);

        Double actual = 0.0;
        BankingRequestDTO bankingRequestDTO =
                createBalanceRequest(account.getAccountId());
        Double balance = accountService.getBalance(bankingRequestDTO);
        Assert.assertEquals("Balance should be equal to " + account + " for " +
                        "newly created account",
                balance, actual);

        Double newAmount = 1.0;
        String transactionId =
                bankingService.creditMoneyIntoAccount(createAddMoneyRequest(account.getAccountId(),
                        1.0));
        assertNotNull("transaction id cannot be null", transactionId);

        TransactionStatus transactionStatus = null;
        while (transactionStatus != TransactionStatus.COMPLETED) {
            transactionStatus =
                    bankingService.getTransactionStatus(transactionId);
        }

        Double newBalance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be ", newBalance, newAmount);
    }

    @Test
    public void deleteAccount() {
        Account account = accountService.createAccount(createAccountRequest());
        assertNotNull("Account cannnot be null", account);
        accountService.deleteAccount(account);

        Account deletedAccount = accountService.getAccount(account);
        assertNull("Account should be deleted ", deletedAccount);
    }

    private BankingRequestDTO createAddMoneyRequest(String accountId,
                                                    double amount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        bankingRequestDTO.setAmount(amount);
        return bankingRequestDTO;
    }

    private BankingRequestDTO createBalanceRequest(String accountId) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        return bankingRequestDTO;
    }

    private Account createAccountRequest() {
        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        return account;
    }
}