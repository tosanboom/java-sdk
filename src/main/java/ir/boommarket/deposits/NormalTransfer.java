package ir.boommarket.deposits;

/**
 * @author Marjan Mehranfar
 */
public class NormalTransfer {
    private String trackingCode;

    /**
     * The tracking code to trace the transaction
     */
    public String trackingCode() {
        return trackingCode;
    }

    @Override
    public String toString() {
        return "NormalTransfer{" +
                "trackingCode='" + trackingCode + '\'' +
                '}';
    }
}