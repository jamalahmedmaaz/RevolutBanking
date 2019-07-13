package com.revolut.model;

/**
 * We can lot of account types.
 * <p>
 * Ex: Crypto etc.
 * <p>
 * For that we need to add validations
 * <p>
 * Ex: Transferring between Personal to Personal
 * or Business to Person and vices-versa are
 * possible and crypto to crypto is possible
 * but not between cryto and others and vices versa
 * <p>
 * Keeping it simple, but you can scale the Product
 * with adding these new types and adding validations.
 */

/**
 * The enum Account type.
 */
public enum AccountType {
    /**
     * Personal account type.
     */
    PERSONAL,
    /**
     * Business account type.
     */
    BUSINESS
}
