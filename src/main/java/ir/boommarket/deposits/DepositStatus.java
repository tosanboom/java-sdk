package ir.boommarket.deposits;

/**
 * Determine the status of deposit
 *
 * It can be:
 * <pre>
 *                CLOSE,
 *                OPEN,
 *                NEGATIVE_BLOCK,
 *                POSITIVE_BLOCK,
 *                BLOCK,
 *                RESTING,
 *                OLD,
 *                OPENING,
 *                UNKNOWN
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public enum DepositStatus {
    CLOSE,
    OPEN,
    NEGATIVE_BLOCK,
    POSITIVE_BLOCK,
    BLOCK,
    RESTING,
    OLD,
    OPENING,
    UNKNOWN
}