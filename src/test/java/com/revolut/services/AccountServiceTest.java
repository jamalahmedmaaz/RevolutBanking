package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;
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

    private Account createAccountRequest() {
        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        return account;
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            fail("failed while sleeping " + interruptedException.getMessage());
        }

        Double newBalance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be ", newBalance, newAmount);
    }

    private BankingRequestDTO createAddMoneyRequest(String accountId,
                                                    double amount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setDestinationAccountId(accountId);
        bankingRequestDTO.setAmount(amount);
        return bankingRequestDTO;
    }

    private BankingRequestDTO createBalanceRequest(String accountId) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        return bankingRequestDTO;
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void deleteAccount() {
    }
}