package com.tosanboom.deposits;

/**
 * It determines the signature's owner
 *
 * It can be:
 *   <pre>
 *                  DEPOSIT_OWNER,
 *                  OWNER_OF_DEPOSIT_AND_SIGNATURE,
 *                  SIGNATURE_OWNER,
 *                  BROKER,
 *                  UNKNOWN
 *   </pre>
 *
 * @author Marjan Mehranfar
 */
public enum SignatureOwnerStatus {
    DEPOSIT_OWNER,
    OWNER_OF_DEPOSIT_AND_SIGNATURE,
    SIGNATURE_OWNER,
    BROKER,
    UNKNOWN
}