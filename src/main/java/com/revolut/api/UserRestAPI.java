package com.revolut.api;

import com.revolut.dtos.UserRequestDTO;
import com.revolut.dtos.UserResponseDTO;
import com.revolut.model.User;
import com.revolut.services.UserService;
import com.revolut.util.JsonUtil;

/**
 * The type User rest api.
 */
public class UserRestAPI {

    private UserService userService;

    /**
     * Instantiates a new User rest api.
     */
    public UserRestAPI() {
        userService = UserService.getUserService();
    }

    /**
     * Gets user info.
     *
     * @param requestObject the request object
     * @return the user info
     */
    public UserResponseDTO getUserInfo(String requestObject) {
        UserRequestDTO userRequestDTO = JsonUtil.readObject(requestObject,
                UserRequestDTO.class);
        User user = userService.getUser(userRequestDTO.getUser());
        return new UserResponseDTO(user);
    }

    /**
     * Create user user response dto.
     *
     * @param requestObject the request object
     * @return the user response dto
     */
    public UserResponseDTO createUser(String requestObject) {
        UserRequestDTO userRequestDTO = JsonUtil.readObject(requestObject,
                UserRequestDTO.class);
        User user = userService.createUser(userRequestDTO.getUser());
        return new UserResponseDTO(user);
    }

    /**
     * Update user user response dto.
     *
     * @param requestObject the request object
     * @return the user response dto
     */
    public UserResponseDTO updateUser(String requestObject) {
        UserRequestDTO userRequestDTO = JsonUtil.readObject(requestObject,
                UserRequestDTO.class);
        User user = userService.updateUser(userRequestDTO.getUser());
        return new UserResponseDTO(user);
    }

    /**
     * Delete user user response dto.
     *
     * @param requestObject the request object
     * @return the user response dto
     */
    public UserResponseDTO deleteUser(String requestObject) {
        UserRequestDTO userRequestDTO = JsonUtil.readObject(requestObject,
                UserRequestDTO.class);
        userService.deleteUser(userRequestDTO.getUser());
        return new UserResponseDTO();
    }
}
