package ir.boommarket.deposits;

/**
 * @author Marjan Mehranfar
 */
public class CancelAutoTransfer {
    private short disableTransferNumber;

    /**
     * Represents the number of auto transfer have been disabled.
     */
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