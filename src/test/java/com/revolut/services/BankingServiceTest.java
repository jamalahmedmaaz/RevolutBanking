package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;
import com.revolut.model.TransactionStatus;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BankingServiceTest {

    private BankingService bankingService =
            BankingServiceImpl.getBankingService();
    private AccountService accountService =
            AccountServiceImpl.getAccountService();

    @Test
    public void creditMoneyIntoAccount() {

        Account account = accountService.createAccount(createAccountRequest());
        assertNotNull("Account can not be null", account);

        Double actual = 0.0;
        BankingRequestDTO bankingRequestDTO =
                createCreditRequest(account.getAccountId());
        Double balance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be equal to " + account + " for " +
                        "newly created account",
                balance, actual);

        Double transferAmount = 100.0;

        String transactionId =
                bankingService.creditMoneyIntoAccount(createCreditRequest(account.getAccountId(), transferAmount));

        assertNotNull("transaction id cannot be null", transactionId);

        TransactionStatus transactionStatus = null;
        while (transactionStatus != TransactionStatus.COMPLETED) {
            transactionStatus =
                    bankingService.getTransactionStatus(transactionId);
        }

        Double newBalance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be ", newBalance, transferAmount);

    }

    @Test
    public void debitMoneyFromAccount() {
        Account account = accountService.createAccount(createAccountRequest());
        assertNotNull("Account can not be null", account);

        Double actual = 0.0;
        BankingRequestDTO bankingRequestDTO =
                createCreditRequest(account.getAccountId());
        Double balance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be equal to " + account + " for " +
                        "newly created account",
                balance, actual);

        Double transferAmount = 100.0;

        String creditTransactionId =
                bankingService.creditMoneyIntoAccount(createCreditRequest(account.getAccountId(), transferAmount));

        assertNotNull("transaction id cannot be null", creditTransactionId);

        TransactionStatus transactionStatus = null;
        while (transactionStatus != TransactionStatus.COMPLETED) {
            transactionStatus =
                    bankingService.getTransactionStatus(creditTransactionId);
        }

        Double balanceAfterTransfer =
                accountService.getBalance(bankingRequestDTO);

        assertEquals("Balance should be equal to " + account + " for " +
                        "newly created account",
                balanceAfterTransfer, transferAmount);

        Double debitAmount = 50.0;

        Double newBalance = accountService.getBalance(bankingRequestDTO);
        assertEquals("Balance should be ", newBalance, transferAmount);

        String debitTransactionId =
                bankingService.debitMoneyFromAccount(createDebitRequest(account.getAccountId(), debitAmount));

        assertNotNull("transaction id cannot be null", debitTransactionId);

        TransactionStatus debitTransactionStatus = null;
        while (debitTransactionStatus != TransactionStatus.COMPLETED) {
            debitTransactionStatus =
                    bankingService.getTransactionStatus(debitTransactionId);
        }

        double currentBalance = accountService.getBalance(bankingRequestDTO);

        assertEquals("Balance should be ", currentBalance,
                (transferAmount - debitAmount), 0.0);

    }

    private BankingRequestDTO createDebitRequest(String accountId,
                                                 Double debitAmount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setDestinationAccountId(accountId);
        bankingRequestDTO.setAmount(debitAmount);
        return bankingRequestDTO;
    }

    @Test
    public void transferMoneyFromOneAccountToAnother() {

    }

    private BankingRequestDTO createAddMoneyRequest(String accountId,
                                                    double amount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setDestinationAccountId(accountId);
        bankingRequestDTO.setAmount(amount);
        return bankingRequestDTO;
    }

    private BankingRequestDTO createCreditRequest(String accountId) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        return bankingRequestDTO;
    }

    private Account createAccountRequest() {
        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        return account;
    }

    private BankingRequestDTO createCreditRequest(String accountId,
                                                  Double transferAmount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setDestinationAccountId(accountId);
        bankingRequestDTO.setAmount(transferAmount);
        return bankingRequestDTO;
    }


}