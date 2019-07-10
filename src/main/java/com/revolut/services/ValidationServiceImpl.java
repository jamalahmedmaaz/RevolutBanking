package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.exceptions.BankingException;
/**
 * The type Validation service.
 */
public class ValidationServiceImpl implements ValidationService {

    private static ValidationService validationService;
    private final BankingService bankingService;

    private ValidationServiceImpl() {
        bankingService = BankingServiceImpl.getBankingService();
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
    public void validateIfAccountExists(String accountId) {
        if (!bankingService.checkIfAccountExists(accountId)) {
            throw new BankingException("Account doesnt exists");
        }
    }

    @Override
    public void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO) {
        validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        validateIfAccountExists(bankingRequestDTO.getSourceAccountId());
        double balance =
                bankingService.viewBalanceOfAccount(bankingRequestDTO);

        if (balance < bankingRequestDTO.getAmount()) {
            throw new BankingException("Insufficient amount in account with " +
                    "account Id " + bankingRequestDTO.getSourceAccountId());
        }

    }

}
