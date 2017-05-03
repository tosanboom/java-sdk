package com.tosanboom.ach;

/**
 * @author Mona Mohamadinia
 */
public enum TransactionStatus {
    READY_FOR_PROCESS,
    SUSPENDED,
    CANCELED,
    PROCESS_FAIL,
    READY_TO_TRANSFER,
    TRANSFERRED,
    SETTLED,
    NOT_SETTLED,
    REJECTED,
    UNKNOWN;
    //TODO If set Unknown in request?!!!
}
