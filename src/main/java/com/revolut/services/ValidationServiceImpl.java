package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.exceptions.BankingException;
import com.revolut.model.AccountStatus;
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
    public void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO) {
        validateAccount(bankingRequestDTO.getDestinationAccountId());
        validateAccount(bankingRequestDTO.getSourceAccountId());
        validateIfSufficientFundExists(bankingRequestDTO);
    }

    @Override
    public void validateAccount(String accountId) {
        if (!bankingModel.doesAccountExists(accountId)) {
            throw new BankingException("[BANKING_EXCEPTION] Account doesnt " +
                    "exists");
        }
        if (bankingModel.getAccount(accountId).getAccountStatus() != AccountStatus.ACTIVE) {
            throw new BankingException("[BANKING_EXCEPTION] Cannot Perform the activity, " +
                    "account is not in active state, please contact admin");
        }
    }

    @Override
    public void validateIfSufficientFundExists(BankingRequestDTO bankingRequestDTO) {
        validateAccount(bankingRequestDTO.getSourceAccountId());
        double balance =
                bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());

        if (balance < bankingRequestDTO.getAmount()) {
            throw new BankingException("[BANKING_EXCEPTION] Insufficient " +
                    "amount in account with " +
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
        validateAccount(accountId);
        double balance =
                bankingModel.getAccountBalance(accountId);

        if (balance < amountToDeduce) {
            throw new BankingException("[BANKING_EXCEPTION] Insufficient " +
                    "amount in account with " +
                    "account Id " + accountId);
        }
    }

}
