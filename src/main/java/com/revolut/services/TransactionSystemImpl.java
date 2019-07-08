package com.revolut.services;

import com.revolut.dtos.BankingRequest;
import com.revolut.queue.QueuingSystem;
import com.revolut.queue.Transaction;
import com.revolut.queue.TransactionType;

import java.util.UUID;

public class TransactionSystemImpl implements TransactionSystem {

    private QueuingSystem queuingSystem;

    public TransactionSystemImpl() {
        this.queuingSystem = QueuingSystem.getInstance();
    }

    @Override
    public void transferMoneyBetweenAccounts(BankingRequest bankingRequest) {
        queuingSystem.addTransactionIntoQueue(new Transaction(UUID.randomUUID().toString(),
                TransactionType.DEBIT_AND_CREDIT,
                bankingRequest.getFromAccountId(),
                bankingRequest.getToAccountId(), bankingRequest.getAmount(),
                bankingRequest.getTransactionTime()));
    }

    @Override
    public void addMoneyIntoAccount(BankingRequest bankingRequest) {
        queuingSystem.addTransactionIntoQueue(new Transaction(UUID.randomUUID().toString(),
                TransactionType.CREDIT,
                bankingRequest.getToAccountId(), null,
                bankingRequest.getAmount(),
                bankingRequest.getTransactionTime()));
    }

    @Override
    public void deduceMoneyFromAccount(BankingRequest bankingRequest) {
        queuingSystem.addTransactionIntoQueue(new Transaction(UUID.randomUUID().toString(),
                TransactionType.DEBIT, null,
                bankingRequest.getToAccountId(), bankingRequest.getAmount(),
                bankingRequest.getTransactionTime()));
    }
}
