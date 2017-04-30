package com.tosanboom.deposits;

/**
 * @author Marjan Mehranfar
 */
public class CancelAutoTransfer {
    private short disableTransferNumber;

    public short disableTransferNumber() {
        return disableTransferNumber;
    }

    @Override
    public String toString() {
        return "CancelAutoTransfer{" +
                "disableTransferNumber=" + disableTransferNumber +
                '}';
    }
}