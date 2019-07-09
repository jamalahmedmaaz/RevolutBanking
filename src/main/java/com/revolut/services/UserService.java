package com.revolut.services;

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
        return bankingModel.createUser(user);
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(User user) {
    }
}
