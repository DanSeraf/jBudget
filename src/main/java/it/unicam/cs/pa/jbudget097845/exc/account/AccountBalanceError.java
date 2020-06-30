package it.unicam.cs.pa.jbudget097845.exc.account;

/**
 * Exception in case of a balance error.
 *
 * Example: trying to add a debit movement to an asset account that should not go under the zero.
 *
 */
public class AccountBalanceError extends AccountException {
    public AccountBalanceError(String msg) {
        super(msg);
    }
}
