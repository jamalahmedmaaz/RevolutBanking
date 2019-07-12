package com.revolut.queue;

import com.revolut.model.BankingModel;
import com.revolut.model.Transaction;
import com.revolut.model.TransactionStatus;
import com.revolut.services.ValidationService;
import com.revolut.services.ValidationServiceImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The type Queuing system.
 */
public final class QueuingSystem {

    private static QueuingSystem instance = null;
    private static BlockingQueue<String> eventQueue = null;
    private final BankingModel bankingModel;
    private final ValidationService validationService;

    private QueuingSystem() {
        this.bankingModel = BankingModel.getBankingModel();
        this.validationService =
                ValidationServiceImpl.getValidationService();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static QueuingSystem getInstance() {
        if (instance == null) {
            synchronized (QueuingSystem.class) {
                instance = new QueuingSystem();
            }
        }
        return instance;
    }
    private void initialize() {
        if (eventQueue == null) {
            synchronized (LinkedBlockingQueue.class) {
                eventQueue = new LinkedBlockingQueue<>();
            }
            EventProcessor eventProcessor = new EventProcessor();
            eventProcessor.start();
        }
    }

    /**
     * Add transaction into queue.
     *
     * @param transaction the transaction
     */
    public void addTransactionIntoQueue(Transaction transaction) {
        try {
            initialize();
            bankingModel.addTransaction(transaction);
            eventQueue.put(transaction.getTransactionId());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    /**
     * The type Event processor.
     */
    class EventProcessor extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                Transaction transaction = null;
                try {
                    transaction =
                            bankingModel.getTransaction(eventQueue.take());
                    if (transaction == null) {
                        continue;
                    }
                    switch (transaction.getTransactionType()) {
                        case CREDIT:
                            /**
                             * Validate the accounts for existence and and are they active.
                             *
                             */
                            validationService.validateAccount(transaction.getSourceAccountId());
                            bankingModel.addAmountIntoAccount(transaction.getSourceAccountId(), transaction.getAmount(), transaction.getTransactionTime());
                            break;
                        case DEBIT:
                            /**
                             * Validate the accounts for existence and and are they active.
                             *
                             * Also validate if account have sufficient funds
                             */
                            validationService.validateIfSufficientFundExists(transaction.getSourceAccountId(), transaction.getAmount());
                            bankingModel.reduceAmountFromAccount(transaction.getSourceAccountId(),
                                    transaction.getAmount(),
                                    transaction.getTransactionTime());
                            break;
                        case DEBIT_AND_CREDIT:

                            /**
                             * Validate both the accounts for existence and and are they active.
                             *
                             * Also validate if source account have sufficient funds
                             */
                            validateTransferRequest(transaction);

                            bankingModel.reduceAmountFromAccount(transaction.getSourceAccountId(),
                                    transaction.getAmount(),
                                    transaction.getTransactionTime());

                            bankingModel.addAmountIntoAccount(transaction.getDestinationAccountId(), transaction.getAmount(), transaction.getTransactionTime());
                            break;
                    }
                    transaction.setTransactionStatus(TransactionStatus.COMPLETED);
                } catch (Exception exception) {
                    transaction.setTransactionStatus(TransactionStatus.ERROR);
                    transaction.setMessage(exception.getMessage());
                    exception.printStackTrace();
                    System.out.println("[QUEUE] Exception while executing " +
                            "transaction, exception message " + exception.getMessage());
                }
            }
        }

        private void validateTransferRequest(Transaction transaction) {
            validationService.validateIfSufficientFundExists(transaction.getSourceAccountId(), transaction.getAmount());
            validationService.validateAccount(transaction.getDestinationAccountId());
        }
    }
}