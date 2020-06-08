package it.unicam.cs.pa.jbudget097845.exc;

/**
 *
 * Exception in case of problem while generating a new account.
 *
 */
public class AccountCreationError extends AccountException {
    public AccountCreationError(String msg) {
        super(msg);
    }
}
