package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revolut.model.User;

import java.io.Serializable;

/**
 * The type User request dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDTO implements Serializable {

    private static final long serialVersionUID = -5139647823139265329L;
    private User user;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
