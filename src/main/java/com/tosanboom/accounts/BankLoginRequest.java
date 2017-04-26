package com.tosanboom.accounts;

import com.tosanboom.Asserts;

/**
 * Encapsulates bank account information which will be used for
 * bank authentication
 *
 * @author Ali Dehghani
 */
public class BankLoginRequest {
    final String username;
    final String password;

    /**
     * Creating a valid instance for {@linkplain BankLoginRequest}
     *
     * @param username The username of a bank account
     * @param password The password of the corresponding user
     * @throws IllegalArgumentException If either of required parameters were missing or invalid
     */
    public BankLoginRequest(String username, String password) {
        Asserts.notBlank(username, "username can't be a blank string");
        Asserts.notBlank(password, "password can't be a blank string");

        this.username = username;
        this.password = password;
    }
}