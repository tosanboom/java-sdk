package com.tosanboom.ach;

/**
 * @author Mona Mohamadinia
 */
public enum AchTransferStatus {
    WAIT_FOR_CUSTOMER_ACCEPT,
    WAIT_FOR_BRANCH_ACCEPT,
    BRANCH_REJECT,
    READY_TO_TRANSFER,
    SUSPEND,
    CANCEL,
    PROCESSED;
}
