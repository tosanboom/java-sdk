package ir.boommarket.deposits;

/**
 * The group of deposit.
 *
 * It can be:
 * <pre>
 *               CURRENT_ACCOUNT,
 *               SPECIAL_LONG_ACCOUNT,
 *               OTHERS,
 *               MANAGED_FUNDS_ACCOUNT,
 *               RESERVE_ACCOUNT,
 *               SPECIAL_SHORT_ACCOUNT,
 *               SAVING_ACCOUNT,
 *               SHORT_ACCOUNT,
 *               LONG_ACCOUNT,
 *               UNKNOWN;
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public enum DepositGroup {
    CURRENT_ACCOUNT,
    SPECIAL_LONG_ACCOUNT,
    OTHERS,
    MANAGED_FUNDS_ACCOUNT,
    RESERVE_ACCOUNT,
    SPECIAL_SHORT_ACCOUNT,
    SAVING_ACCOUNT,
    SHORT_ACCOUNT,
    LONG_ACCOUNT,
    UNKNOWN
}