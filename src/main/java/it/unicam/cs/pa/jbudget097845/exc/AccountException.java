package it.unicam.cs.pa.jbudget097845.exc;

/**
 * Abstract class for exceptions of type Account
 */
public abstract class AccountException extends RuntimeException {

    private String message;

    public AccountException(String msg) {
        this.message = msg;
    }
}
