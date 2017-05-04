package ir.boommarket.deposits;

/**
 * The state of support's deposit
 *
 * It can be:
 * <pre>
 *         {@code ACCOUNT},
 *         {@code DEPOSIT},
 *         {@code UNKNOWN}
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public enum SupportStatus {
    ACCOUNT,
    DEPOSIT,
    UNKNOWN
}