package com.revolut.services;
import com.revolut.model.BankingModel;
import com.revolut.model.User;

public class UserService {

    private static UserService userService;

    private BankingModel bankingModel = BankingModel.getBankingModel();

    private UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                userService = new UserService();
            }
        }
        return userService;
    }

    public User createUser(User user) {
        return bankingModel.createUser(user);
    }
}
