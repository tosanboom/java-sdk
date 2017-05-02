package com.tosanboom.deposits;

/**
 * The state of auto transfer
 * <p> It can be:
 * <pre>
 *     FINISHED,
 *     ACTIVE,
 *     CANCELED,
 *     UNSUCCESSFUL,
 *     UNKNOWN
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public enum AutoTransferStatus {
    FINISHED,
    ACTIVE,
    CANCELED,
    UNSUCCESSFUL,
    UNKNOWN
}