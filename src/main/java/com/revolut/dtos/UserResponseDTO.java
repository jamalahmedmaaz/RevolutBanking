package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revolut.model.User;

import java.io.Serializable;

/**
 * The type User response dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO implements Serializable {
    private static final long serialVersionUID = -2035419163568590125L;
    private User user;

    /**
     * Instantiates a new User response dto.
     *
     * @param user the user
     */
    public UserResponseDTO(User user) {
        this.user = user;
    }

    public UserResponseDTO() {

    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
}
