package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revolut.model.Account;

import java.io.Serializable;

/**
 * The type Account response dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResponseDTO implements Serializable {
    private static final long serialVersionUID = -8149433311577221486L;
    private Account account;

    /**
     * Instantiates a new Account response dto.
     */
    public AccountResponseDTO() {
    }

    /**
     * Instantiates a new Account response dto.
     *
     * @param account the account
     */
    public AccountResponseDTO(Account account) {
        this.account = account;
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
