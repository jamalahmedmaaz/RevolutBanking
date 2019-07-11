package com.revolut.services;

import com.revolut.model.Account;
import com.revolut.model.BankingModel;
import com.revolut.model.User;

/**
 * The type User service.
 */
public class UserService {

    private static UserService userService;
    private BankingModel bankingModel;

    private UserService() {
        bankingModel = BankingModel.getBankingModel();
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                userService = new UserService();
            }
        }
        return userService;
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    public User createUser(User user) {
        Account account = bankingModel.createAccount(new Account());
        user.getAccounts().add(account.getAccountId());
        return bankingModel.createUser(user);
    }

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    public User updateUser(User user) {
        return bankingModel.updateUser(user);
    }

    /**
     * Delete user.
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        bankingModel.deleteUser(user.getUserId());
    }

    /**
     * Gets user.
     *
     * @param user the user
     * @return the user
     */
    public User getUser(User user) {
        return bankingModel.getUser(user.getUserId());
    }
}
