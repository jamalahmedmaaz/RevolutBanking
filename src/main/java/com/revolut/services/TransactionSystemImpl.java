package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Transaction;
import com.revolut.model.TransactionStatus;
import com.revolut.model.TransactionType;
import com.revolut.queue.QueuingSystem;

import java.util.UUID;

/**
 * The type Transaction system.
 */
public class TransactionSystemImpl implements TransactionSystem {

    private static volatile TransactionSystem transactionSystem;
    private QueuingSystem queuingSystem;

    private TransactionSystemImpl() {
        this.queuingSystem = QueuingSystem.getInstance();
    }

    /**
     * Gets transaction system.
     *
     * @return the transaction system
     */
    public static TransactionSystem getTransactionSystem() {
        if (transactionSystem == null) {
            synchronized (TransactionSystemImpl.class) {
                if (transactionSystem == null) {
                    transactionSystem = new TransactionSystemImpl();
                }
            }
        }
        return transactionSystem;
    }

    @Override
    public String transferMoneyBetweenAccounts(BankingRequestDTO bankingRequestDTO) {
        String transactionId = UUID.randomUUID().toString();
        queuingSystem.addTransactionIntoQueue(new Transaction(transactionId,
                TransactionType.DEBIT_AND_CREDIT,
                bankingRequestDTO.getSourceAccountId(),
                bankingRequestDTO.getDestinationAccountId(),
                bankingRequestDTO.getAmount(),
                bankingRequestDTO.getTransactionTime(),
                TransactionStatus.IN_PROGRESS));
        return transactionId;
    }

    @Override
    public String addMoneyIntoAccount(BankingRequestDTO bankingRequestDTO) {
        String transactionId = UUID.randomUUID().toString();
        queuingSystem.addTransactionIntoQueue(new Transaction(transactionId,
                TransactionType.CREDIT,
                bankingRequestDTO.getSourceAccountId(),
                bankingRequestDTO.getAmount(),
                bankingRequestDTO.getTransactionTime(),
                TransactionStatus.IN_PROGRESS));
        return transactionId;
    }

    @Override
    public String deduceMoneyFromAccount(BankingRequestDTO bankingRequestDTO) {
        String transactionId = UUID.randomUUID().toString();
        queuingSystem.addTransactionIntoQueue(new Transaction(transactionId,
                TransactionType.DEBIT,
                bankingRequestDTO.getSourceAccountId(),
                bankingRequestDTO.getAmount(),
                bankingRequestDTO.getTransactionTime(),
                TransactionStatus.IN_PROGRESS));
        return transactionId;
    }
}
