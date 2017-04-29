package com.tosanboom.cards;

/**
 * Encapsulates personal information of the card holder
 *
 * @author Marjan Mehranfar
 */
public class CardHolder {
    private String firstName;
    private String lastName;

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "CardHolder{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
