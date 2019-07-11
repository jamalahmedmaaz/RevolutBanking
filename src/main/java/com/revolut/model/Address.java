package com.revolut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
/**
 * The type Address.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {
    private static final long serialVersionUID = 520056990372756517L;

    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String pincode;

    /**
     * Gets address line 1.
     *
     * @return the address line 1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets address line 1.
     *
     * @param addressLine1 the address line 1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets address line 2.
     *
     * @return the address line 2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets address line 2.
     *
     * @param addressLine2 the address line 2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets pincode.
     *
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets pincode.
     *
     * @param pincode the pincode
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
