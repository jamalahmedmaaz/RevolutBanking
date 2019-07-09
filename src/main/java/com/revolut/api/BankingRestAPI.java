package com.revolut.api;

import com.revolut.annotations.RevolutApi;
import com.revolut.annotations.RevolutApiPath;
import com.revolut.dtos.BankingRequestDTO;
import com.revolut.dtos.BankingResponseDTO;
import com.revolut.services.BankingService;
import com.revolut.services.BankingServiceImpl;
import com.revolut.util.JsonUtil;

/**
 * The type Banking rest api.
 */
@RevolutApi
public class BankingRestAPI {

    private final BankingService bankingService;

    /**
     * Instantiates a new Banking rest api.
     */
    public BankingRestAPI() {
        this.bankingService = BankingServiceImpl.getBankingService();
    }

    /**
     * Add money banking response dto.
     *
     * @param requestObject the banking request dto
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO addMoney(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        String transactionId = bankingService.addMoney(bankingRequestDTO);
        return new BankingResponseDTO(transactionId);
    }

    /**
     * View balance banking response dto.
     *
     * @param requestObject the banking request dto
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO viewBalance(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        double balance = bankingService.viewBalance(bankingRequestDTO);
        return new BankingResponseDTO(balance);
    }
}
