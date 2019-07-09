package com.revolut.api;

import com.revolut.annotations.RevolutApi;
import com.revolut.annotations.RevolutApiPath;
import com.revolut.dtos.AccountRequestDTO;
import com.revolut.dtos.AccountResponseDTO;
import com.revolut.services.AccountService;
import com.revolut.services.AccountServiceImpl;
import com.revolut.util.JsonUtil;

/**
 * The type Account rest api.
 */
@RevolutApi
public class AccountRestAPI {

    private AccountService accountService;

    /**
     * Instantiates a new Account rest api.
     */
    public AccountRestAPI() {
        this.accountService = AccountServiceImpl.getAccountService();
    }

    /**
     * Create account account response dto.
     *
     * @param requestObject the requestObject
     * @return the account response dto
     */
    @RevolutApiPath
    public AccountResponseDTO createAccount(String requestObject) {
        AccountRequestDTO accountRequestDTO = JsonUtil.readObject(requestObject,
                AccountRequestDTO.class);
        return new AccountResponseDTO(accountService.createAccount(accountRequestDTO.getAccount()));
    }

    /**
     * Update account account response dto.
     *
     * @param requestObject the requestObject
     * @return the account response dto
     */
    @RevolutApiPath
    public AccountResponseDTO updateAccount(String requestObject) {
        AccountRequestDTO accountRequestDTO =
                JsonUtil.readObject(requestObject,
                        AccountRequestDTO.class);
        accountService.updateAccount(accountRequestDTO.getAccount());
        return new AccountResponseDTO();
    }

    /**
     * Delete account account response dto.
     *
     * @param requestObject the requestObject
     * @return the account response dto
     */
    @RevolutApiPath
    public AccountResponseDTO deleteAccount(String requestObject) {
        AccountRequestDTO accountRequestDTO =
                JsonUtil.readObject(requestObject,
                        AccountRequestDTO.class);
        accountService.deleteAccount(accountRequestDTO.getAccount());
        return new AccountResponseDTO();
    }
}
