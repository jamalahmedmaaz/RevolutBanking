package com.revolut.api;
import com.revolut.dtos.UserRequestDTO;
import com.revolut.dtos.UserResponseDTO;
import com.revolut.model.User;
import com.revolut.services.UserService;

public class UserRestAPI {

    private UserService userService;

    public UserRestAPI() {
        userService = UserService.getUserService();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO.getUser());
        return new UserResponseDTO(user);
    }
}
