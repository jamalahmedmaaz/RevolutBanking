package com.revolut.dtos;
import com.revolut.model.User;

public class UserResponseDTO {
    private final User user;

    public UserResponseDTO(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
