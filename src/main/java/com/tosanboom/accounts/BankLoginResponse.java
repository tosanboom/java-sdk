package com.tosanboom.accounts;

import java.util.Date;

/**
 * Represents a successful login response
 *
 * @author Ali Dehghani
 */
public class BankLoginResponse {
    private String sessionId;
    private Date lastLoginDate;
    private Gender gender;
    private String name;
    private String code;
    private String title;
    private String foreignName;

    public String sessionId() {
        return sessionId;
    }

    public Date lastLoginDate() {
        return lastLoginDate;
    }

    public Gender gender() {
        return gender;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

    public String foreignName() {
        return foreignName;
    }
}