package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.exceptions.BankingException;
import com.revolut.exceptions.BankingValidationCode;
import com.revolut.model.AccountStatus;
import com.revolut.model.BankingModel;
import com.revolut.model.Transaction;
/**
 * The type Validation service.
 */
public class ValidationServiceImpl implements ValidationService {

    private static volatile ValidationServiceImpl validationService;
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
                if (validationService == null) {
                    validationService = new ValidationServiceImpl();
                }
            }
        }
        return validationService;
    }

    @Override
    public void validateInActiveAccount(String accountId) {
        basicAccountValidations(accountId);
    }

    private void basicAccountValidations(String accountId) {
        if (accountId == null || accountId.equals("")) {
            throw new BankingException(BankingValidationCode.MISSING_ACCOUNT_ID);
        }

        if (!bankingModel.doesAccountExists(accountId)) {
            throw new BankingException(BankingValidationCode.ACCOUNT_DOESNT_EXISTS);
        }
    }

    @Override
    public void validateTransactionId(String transactionId) {
        Transaction transaction = bankingModel.getTransaction(transactionId);
        if (transaction == null) {
            throw new BankingException(BankingValidationCode.TRANSACTION_DOESNT_EXISTS, "Requested Transaction Id" + transactionId);
        }
    }

    @Override
    public void validateIfSufficientFundExists(String accountId,
                                               double amountToDeduct) {
        validateAccount(accountId);
        double balance =
                bankingModel.getAccountBalance(accountId);

        if (balance < amountToDeduct) {
            throw new BankingException(BankingValidationCode.INSUFFICIENT_ACCOUNT_BALANCE, "Requested Account Id" + accountId);
        }
    }

    @Override
    public void validateAccount(String accountId) {
        basicAccountValidations(accountId);
        if (bankingModel.getAccount(accountId).getAccountStatus() != AccountStatus.ACTIVE) {
            throw new BankingException(BankingValidationCode.ACCOUNT_IS_NOT_ACTIVE);
        }
    }

    @Override
    public void validateCreditRequest(String accountId, double amount) {
        validateAccount(accountId);
        if (amount <= 0) {
            throw new BankingException(BankingValidationCode.CREDITING_AMOUNT_CANNOT_BE_LOW, String.valueOf(amount));
        }
    }

    @Override
    public void validateDebit(BankingRequestDTO bankingRequestDTO) {
        validateIfSufficientFundExists(bankingRequestDTO);
        if (bankingRequestDTO.getAmount() <= 0) {
            throw new BankingException(BankingValidationCode.DEBIT_AMOUNT_CANNOT_BE_LOW, String.valueOf(bankingRequestDTO.getAmount()));
        }
    }

    @Override
    public void validateIfSufficientFundExists(BankingRequestDTO bankingRequestDTO) {
        validateAccount(bankingRequestDTO.getSourceAccountId());
        double balance =
                bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());

        if (balance < bankingRequestDTO.getAmount()) {
            throw new BankingException(BankingValidationCode.INSUFFICIENT_ACCOUNT_BALANCE, "Requested Account Id" + bankingRequestDTO.getSourceAccountId());
        }
    }

    @Override
    public void validateTransfer(BankingRequestDTO bankingRequestDTO) {
        validateIfAccountHaveSufficientFundsToDebit(bankingRequestDTO);
        if (bankingRequestDTO.getAmount() <= 0) {
            throw new BankingException(BankingValidationCode.TRANSFER_AMOUNT_CANNOT_BE_LOW, String.valueOf(bankingRequestDTO.getAmount()));
        }
    }

    @Override
    public void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO) {
        validateAccount(bankingRequestDTO.getDestinationAccountId());
        validateAccount(bankingRequestDTO.getSourceAccountId());
        validateDifferentAccounts(bankingRequestDTO);
        validateIfSufficientFundExists(bankingRequestDTO);
    }

    private void validateDifferentAccounts(BankingRequestDTO bankingRequestDTO) {
        if (bankingRequestDTO.getSourceAccountId().equals(bankingRequestDTO.getDestinationAccountId())) {
            throw new BankingException(BankingValidationCode.SENDER_RECEIVER_CANNOT_BE_SAME);
        }
    }
}
