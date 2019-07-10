package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.queue.QueuingSystem;
import com.revolut.queue.Transaction;
import com.revolut.queue.TransactionType;

import java.util.UUID;

/**
 * The type Transaction system.
 */
public class TransactionSystemImpl implements TransactionSystem {

    private static TransactionSystem transactionSystem;
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
            synchronized (BankingServiceImpl.class) {
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
                bankingRequestDTO.getTransactionTime()));
        return transactionId;
    }

    @Override
    public String addMoneyIntoAccount(BankingRequestDTO bankingRequestDTO) {
        String transactionId = UUID.randomUUID().toString();
        queuingSystem.addTransactionIntoQueue(new Transaction(transactionId,
                TransactionType.CREDIT,
                bankingRequestDTO.getDestinationAccountId(), null,
                bankingRequestDTO.getAmount(),
                bankingRequestDTO.getTransactionTime()));
        return transactionId;
    }

    @Override
    public String deduceMoneyFromAccount(BankingRequestDTO bankingRequestDTO) {
        String transactionId = UUID.randomUUID().toString();
        queuingSystem.addTransactionIntoQueue(new Transaction(transactionId,
                TransactionType.DEBIT, null,
                bankingRequestDTO.getDestinationAccountId(),
                bankingRequestDTO.getAmount(),
                bankingRequestDTO.getTransactionTime()));
        return transactionId;
    }
}
