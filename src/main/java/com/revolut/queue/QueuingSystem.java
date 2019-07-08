package com.revolut.queue;

import com.revolut.model.BankingModel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class QueuingSystem {

    private static QueuingSystem instance = null;
    private static BlockingQueue<Transaction> eventQueue = null;
    private static BankingModel bankingModel = BankingModel.getBankingModel();

    private QueuingSystem() {
    }

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

    public void addTransactionIntoQueue(Transaction transaction) {
        try {
            initialize();
            eventQueue.put(transaction);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    class EventProcessor extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                Transaction transaction = null;
                try {
                    transaction = eventQueue.take();
                    switch (transaction.getTransactionType()) {
                        case CREDIT:
                            bankingModel.addAmountIntoAccount(transaction.getToAccountId(), transaction.getAmount(), transaction.getTransactionTime());
                            break;
                        case DEBIT:
                            bankingModel.reduceAmountFromAccount(transaction.getToAccountId(),
                                    transaction.getAmount(),
                                    transaction.getTransactionTime());
                            break;
                        case DEBIT_AND_CREDIT:
                            try {
                                bankingModel.addAmountIntoAccount(transaction.getToAccountId(), transaction.getAmount(), transaction.getTransactionTime());
                                bankingModel.reduceAmountFromAccount(transaction.getToAccountId(),
                                        transaction.getAmount(),
                                        transaction.getTransactionTime());
                            } catch (Exception exception) {
                                System.out.println(exception.getMessage());
                            }
                            break;
                    }
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }
}