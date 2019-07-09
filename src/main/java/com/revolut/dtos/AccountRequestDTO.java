package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revolut.model.Account;

import java.io.Serializable;

/**
 * The type Account request dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequestDTO implements Serializable {
    private static final long serialVersionUID = -3110032686622174573L;

    private Account account;

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
