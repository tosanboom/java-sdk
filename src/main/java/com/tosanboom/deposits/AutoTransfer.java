package com.tosanboom.deposits;

/**
 * @author Marjan Mehranfar
 */
public class AutoTransfer {
    private String trackingNumber;

    public String trackingNumber() {
        return trackingNumber;
    }

    @Override
    public String toString() {
        return "AutoTransfer{" +
                "trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}