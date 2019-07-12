package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.exceptions.BankingException;
import com.revolut.exceptions.BankingValidationCode;
import com.revolut.model.Account;
import com.revolut.model.TransactionStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * The type Banking service test.
 */
public class BankingServiceTest {

    private BankingService bankingService;
    private AccountService accountService;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        accountService = AccountServiceImpl.getAccountService();
        bankingService = BankingServiceImpl.getBankingService();
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        accountService = null;
        bankingService = null;
    }

    /**
     * Credit money into account.
     */
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

    /**
     * Debit money from account.
     */
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


    /**
     * Transfer money from one account to another.
     */
    @Test
    public void transferMoneyFromOneAccountToAnother() {
        /**
         *Create two account.
         */

        Account senderAccount =
                accountService.createAccount(createAccountRequest());
        Account receiverAccount =
                accountService.createAccount(createAccountRequest());

        /**
         * Add money to sender account, who will send money to
         * receiver going forward.
         *
         */
        Double amountToAdd = 100.0;
        String transactionId =
                bankingService.creditMoneyIntoAccount(createCreditRequest(senderAccount.getAccountId(), amountToAdd));
        checkTransactionStatus(transactionId);

        double amountToTransfer = 100.0;
        // Keep Sender Original Balance.
        double orginalSenderBalance =
                bankingService.viewBalanceOfAccount(new BankingRequestDTO(senderAccount.getAccountId()));

        //Transfer money from one account to another.
        String transferTransactionId =
                bankingService.transferMoneyFromSenderToReceiver(
                        createTransferRequestBetweenInternalAccounts(senderAccount.getAccountId(), receiverAccount.getAccountId(), amountToTransfer));
        checkTransactionStatus(transferTransactionId);

        double senderAccountBalance =
                bankingService.viewBalanceOfAccount(new BankingRequestDTO(senderAccount.getAccountId()));
        double receiverAccountBalance =
                bankingService.viewBalanceOfAccount(new BankingRequestDTO(receiverAccount.getAccountId()));

        assertTrue("Balance Cannot be negative ", senderAccountBalance >= 0);
        assertTrue("Balance Cannot be negative ", receiverAccountBalance >= 0);

        assertEquals("Correct transfer didnt happen",
                (orginalSenderBalance - amountToTransfer),
                senderAccountBalance, 0.0);

        assertEquals("In Correct transfer ", (amountToTransfer),
                receiverAccountBalance, 0.0);

        continuouslyAddMoneyToAccounts(senderAccount.getAccountId());
        continuouslyAddMoneyToAccounts(receiverAccount.getAccountId());

        continuouslyDebitFromAccounts(senderAccount.getAccountId());
        continuouslyDebitFromAccounts(receiverAccount.getAccountId());

        continuouslyTransferBetweenAccounts(senderAccount.getAccountId(),
                receiverAccount.getAccountId());

        continuouslyViewAccountBalance(senderAccount.getAccountId());
        continuouslyViewAccountBalance(receiverAccount.getAccountId());

        try {
            /**
             * 10 Minutes test.
             */
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            fail("Exception while sleeping " + e.getMessage());
        }

    }

    @Test
    public void testCredit_negative() {
        String nonExistingAccountId = UUID.randomUUID().toString();
        try {
            bankingService.creditMoneyIntoAccount(new BankingRequestDTO(""));
            fail("Should throw exception account Id " + nonExistingAccountId + " doesnt exists");
        } catch (BankingException bankingException) {
            assertEquals("Exception should be: " + bankingException.getLocalizedMessage(),
                    bankingException.getCode(), BankingValidationCode.MISSING_ACCOUNT_ID.getCode());
        }
    }

    @Test
    public void testDebit_negative() {
        String nonExistingAccountId = UUID.randomUUID().toString();
        try {
            bankingService.debitMoneyFromAccount(new BankingRequestDTO(nonExistingAccountId));
            fail("Should throw exception account Id " + nonExistingAccountId + " doesnt exists");
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.ACCOUNT_DOESNT_EXISTS.getCode(), bankingException.getCode());
        }
    }

    @Test
    public void testTransfer_negative() {
        String nonExistingAccountId = UUID.randomUUID().toString();
        BankingRequestDTO bankingRequestDTO = createTransferRequestBetweenInternalAccounts(nonExistingAccountId,
                null, 20.0);
        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.MISSING_ACCOUNT_ID);
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.MISSING_ACCOUNT_ID.getCode(), bankingException.getCode());
        }

        bankingRequestDTO.setDestinationAccountId(nonExistingAccountId);

        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.ACCOUNT_DOESNT_EXISTS);
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.ACCOUNT_DOESNT_EXISTS.getCode(), bankingException.getCode());
        }

        Account senderAccount = accountService.createAccount(createAccountRequest());
        assertNotNull("Account can not be null", senderAccount);
        Double transferAmount = 100.0;
        String transactionId = bankingService.creditMoneyIntoAccount(createCreditRequest(senderAccount.getAccountId(), transferAmount));

        checkTransactionStatus(transactionId);

        bankingRequestDTO.setSourceAccountId(senderAccount.getAccountId());
        bankingRequestDTO.setDestinationAccountId(senderAccount.getAccountId());

        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.SENDER_RECEIVER_CANNOT_BE_SAME.getMessage());
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.SENDER_RECEIVER_CANNOT_BE_SAME.getCode(), bankingException.getCode());
        }

        Account receiverAccount = accountService.createAccount(createAccountRequest());
        assertNotNull("Account can not be null", senderAccount);

        bankingRequestDTO.setSourceAccountId(receiverAccount.getAccountId());
        bankingRequestDTO.setDestinationAccountId(senderAccount.getAccountId());
        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.SENDER_RECEIVER_CANNOT_BE_SAME.getMessage());
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.INSUFFICIENT_ACCOUNT_BALANCE.getCode(), bankingException.getCode());
        }

        bankingRequestDTO.setSourceAccountId(senderAccount.getAccountId());
        bankingRequestDTO.setDestinationAccountId(receiverAccount.getAccountId());

        bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);

        bankingService.blockAccount(senderAccount.getAccountId());

        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.ACCOUNT_IS_NOT_ACTIVE.getMessage());
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.ACCOUNT_IS_NOT_ACTIVE.getCode(), bankingException.getCode());
        }

        bankingService.activateAccount(senderAccount.getAccountId());
        bankingRequestDTO.setSourceAccountId(receiverAccount.getAccountId());
        bankingRequestDTO.setDestinationAccountId(senderAccount.getAccountId());
        bankingRequestDTO.setAmount(-1.0);

        try {
            bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
            fail("Should throw exception account Id " + BankingValidationCode.TRANSFER_AMOUNT_CANNOT_BE_LOW.getMessage());
        } catch (BankingException bankingException) {
            assertEquals("Exception should be " + bankingException.getLocalizedMessage(),
                    BankingValidationCode.TRANSFER_AMOUNT_CANNOT_BE_LOW.getCode(), bankingException.getCode());
        }

    }

    private BankingRequestDTO createCreditRequest(String accountId,
                                                  Double transferAmount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        bankingRequestDTO.setAmount(transferAmount);
        return bankingRequestDTO;
    }

    private void checkTransactionStatus(String transactionId) {
        TransactionStatus transactionStatus = null;
        int count = 0;
        while (true) {
            transactionStatus =
                    bankingService.getTransactionStatus(transactionId);
            if (transactionStatus == TransactionStatus.COMPLETED || transactionStatus == TransactionStatus.ERROR) {
                break;
            }
            try {
                Thread.sleep(3000);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (count > 10) {
                break;
            }
        }
        assertTrue("Transaction Errored out",
                transactionStatus != TransactionStatus.ERROR);
    }

    private void continuouslyViewAccountBalance(String accountId) {
        Thread viewAccountThread = new Thread(() -> {
            try {
                while (true) {
                    double balance =
                            bankingService.viewBalanceOfAccount(new BankingRequestDTO(accountId));

                    System.out.println("[VIEW_BALANCE] Viewing account " +
                            "balance for " +
                            "account: "
                            + accountId + ", balance               " + balance);

                    assertTrue("Balance Cannot be negative", balance >= 0);
                    Thread.sleep(500);
                }
            } catch (InterruptedException v) {
                fail("Exception while waiting " + v.getMessage());
            }
        });

        viewAccountThread.start();
    }

    private void continuouslyTransferBetweenAccounts(String senderAccountId,
                                                     String receiverAccountId) {

        Thread viewAccountThread = new Thread(() -> {
            try {
                while (true) {
                    Random random = new Random();
                    double transferAmountValue = random.nextDouble();

                    bankingService.transferMoneyFromSenderToReceiver(
                            createTransferRequestBetweenInternalAccounts(
                                    senderAccountId,
                                    receiverAccountId,
                                    transferAmountValue));

                    System.out.println("[TRANSFER_FUNDS] transferring :" + transferAmountValue + " from account " + senderAccountId + ", to account " + receiverAccountId);
                    Thread.sleep(500);
                }
            } catch (InterruptedException v) {
                fail("Exception while waiting " + v.getMessage());
            }
        });

        viewAccountThread.start();
    }

    private void continuouslyDebitFromAccounts(String accountId) {
        Thread debitMoneyThread = new Thread(() -> {
            try {
                Random random = new Random();
                while (true) {
                    double debitAmount = random.nextDouble();
                    System.out.println("[DEBIT_MONEY] Debit money from " +
                            "account with " +
                            "account" +
                            " id " + accountId + " amount   " + debitAmount);
                    bankingService.debitMoneyFromAccount(new BankingRequestDTO(accountId,
                            debitAmount));
                    Thread.sleep(500);
                }
            } catch (InterruptedException v) {
                fail("Exception while waiting " + v.getMessage());
            }
        });

        debitMoneyThread.start();
    }

    private void continuouslyAddMoneyToAccounts(String accountId) {
        Thread creditMoneyThread = new Thread(() -> {
            try {
                Random random = new Random();
                while (true) {
                    double creditAmount = random.nextDouble();
                    System.out.println("[CREDIT_MONEY] Credit money from " +
                            "account with " +
                            "account" +
                            " id " + accountId + " amount             " + creditAmount);
                    bankingService.creditMoneyIntoAccount(new BankingRequestDTO(accountId, creditAmount));
                    Thread.sleep(500);
                }
            } catch (InterruptedException v) {
                fail("Exception while waiting " + v.getMessage());
            }
        });

        creditMoneyThread.start();
    }

    private BankingRequestDTO createDebitRequest(String accountId,
                                                 Double debitAmount) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setSourceAccountId(accountId);
        bankingRequestDTO.setAmount(debitAmount);
        return bankingRequestDTO;
    }

    private BankingRequestDTO createTransferRequestBetweenInternalAccounts(String sourceAccountId, String destinationAccountId, Double amountToTransfer) {
        BankingRequestDTO bankingRequestDTO = new BankingRequestDTO();
        bankingRequestDTO.setDestinationAccountId(destinationAccountId);
        bankingRequestDTO.setSourceAccountId(sourceAccountId);
        bankingRequestDTO.setAmount(amountToTransfer);
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

}