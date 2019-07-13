package com.revolut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type User.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private static final long serialVersionUID = -2969314454348145696L;

    private UserType userType;
    private String userId;
    private List<String> accounts;
    private LocalDateTime createDate = LocalDateTime.now();
    private UserInfo userInfo;
    private Gender gender;
    private Salutation salutation;

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets accounts.
     *
     * @return the accounts
     */
    public List<String> getAccounts() {
        return accounts;
    }

    /**
     * Sets accounts.
     *
     * @param accounts the accounts
     */
    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets user info.
     *
     * @return the user info
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets user info.
     *
     * @param userInfo the user info
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets salutation.
     *
     * @return the salutation
     */
    public Salutation getSalutation() {
        return salutation;
    }

    /**
     * Sets salutation.
     *
     * @param salutation the salutation
     */
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }
}
