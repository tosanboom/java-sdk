package com.tosanboom.cards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Encapsulates list of card transactions
 *
 * @author Ali Dehghani
 */
public class CardTransactions {
    /**
     * List of transactions
     */
    public List<Transaction> transactions = new ArrayList<>();

    /**
     * Each transaction meta date in transaction history. Obviously, each transaction
     * would has a source and a destination. The source of a transaction is the one who
     * paid the transaction fee and the destination is the one who getting paid.
     */
    public static class Transaction {
        /**
         * The pan or card number for transaction destination
         */
        public String destinationPan;

        /**
         * The deposit number for transaction destination
         */
        public String destinationDepositNumber;

        /**
         * The date that transaction has been committed
         */
        public Date transactionDate;

        /**
         * Represents the amount of transaction in Rails
         */
        public BigDecimal amount;

        /**
         * Represents the type of the transaction
         */
        public TransactionType transactionType;

        /**
         * Financial institution that issued the source card
         */
        public String issuer;

        /**
         * Financial institution that issued the destination card
         */
        public String destinationIssuer;
        public String referenceNumber;
        public String traceNumber;
        public String description;
    }
}