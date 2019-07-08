package com.revolut.dtos;
import com.revolut.model.User;

public class UserRequestDTO {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
