package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.exceptions.BankingException;
import com.revolut.model.BankingModel;
import com.revolut.model.Transaction;
/**
 * The type Validation service.
 */
public class ValidationServiceImpl implements ValidationService {

    private static ValidationServiceImpl validationService;
    private final BankingModel bankingModel;

    private ValidationServiceImpl() {
        bankingModel = BankingModel.getBankingModel();
    }

    /**
     * Gets validation service.
     *
     * @return the validation service
     */
    public static ValidationService getValidationService() {
        if (validationService == null) {
            synchronized (ValidationServiceImpl.class) {
                validationService = new ValidationServiceImpl();
            }
        }
        return validationService;
    }

    @Override
    public void validateIfAccountExists(String accountId) {
        if (!bankingModel.doesAccountExists(accountId)) {
            throw new BankingException("Account doesnt exists");
        }
    }

    @Override
    public void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO) {
        validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        validateIfAccountExists(bankingRequestDTO.getSourceAccountId());
        double balance =
                bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());

        if (balance < bankingRequestDTO.getAmount()) {
            throw new BankingException("Insufficient amount in account with " +
                    "account Id " + bankingRequestDTO.getSourceAccountId());
        }
    }

    @Override
    public void validateIfSufficientFundExists(BankingRequestDTO bankingRequestDTO) {
        double balance =
                bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());

        if (balance < bankingRequestDTO.getAmount()) {
            throw new BankingException("Insufficient amount in account with " +
                    "account Id " + bankingRequestDTO.getSourceAccountId());
        }
    }

    @Override
    public void validateTransactionId(String transactionId) {
        Transaction transaction = bankingModel.getTransaction(transactionId);
        if (transaction == null) {
            throw new BankingException("The transaction with transaction id " +
                    "does not exists" + transactionId);
        }
    }

    @Override
    public void validateIfSufficientFundExists(String accountId,
                                               double amountToDeduce) {
        double balance =
                bankingModel.getAccountBalance(accountId);

        if (balance < amountToDeduce) {
            throw new BankingException("Insufficient amount in account with " +
                    "account Id " + accountId);
        }
    }

}
