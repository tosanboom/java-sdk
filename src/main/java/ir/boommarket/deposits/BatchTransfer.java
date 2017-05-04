package ir.boommarket.deposits;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class BatchTransfer {
    private String transferId;
    private String currency;
    private BigDecimal balance;
    private boolean doneCompletely;
    private List<DepositProblem> depositProblem = new ArrayList<>();

    /**
     * The id of (batch) transfer to tracking it.
     */
    public String transferId() {
        return transferId;
    }

    /**
     * Represents the currency of source deposit
     */
    public String currency() {
        return currency;
    }

    /**
     * Represents the balance that the user can withdrawal after batch transfer
     */
    public BigDecimal balance() {
        return balance;
    }

    /**
     * Represents that all the requests to transfer were done or not.
     */
    public boolean doneCompletely() {
        return doneCompletely;
    }

    /**
     * List of {@linkplain DepositProblem} represents the deposit number of destination and type of error for each of transactions
     */
    public List<DepositProblem> depositProblem() {
        return depositProblem;
    }

    public static class DepositProblem {
        private String depositNumber;
        private DepositProblemType problemType;

        /**
         * The deposit number of destination
         */
        public String depositNumber() {
            return depositNumber;
        }

        /**
         * Represents type of error for transfer
         */
        public DepositProblemType problemType() {
            return problemType;
        }

        @Override
        public String toString() {
            return "DepositProblem{" +
                    "depositNumber='" + depositNumber + '\'' +
                    ", problemType=" + problemType +
                    '}';
        }
    }

    /**
     * Determine the cause of problem to transfer
     */
    public enum DepositProblemType {
        INVALID_DEPOSIT_NUMBER,
        INVALID_PRICE,
        USER_PERMISSION_DENIED,
        INVALID_CURRENCY,
        INSUFFICIENT_FUNDS,
        ILLEGAL_DEPOSIT_STATE,
        UNKNOWN
    }

    @Override
    public String toString() {
        return "BatchTransfer{" +
                "transferId='" + transferId + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", doneCompletely=" + doneCompletely +
                ", depositProblem=" + depositProblem +
                '}';
    }
}