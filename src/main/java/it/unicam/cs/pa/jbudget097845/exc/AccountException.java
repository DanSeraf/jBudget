package it.unicam.cs.pa.jbudget097845.exc;

public abstract class AccountException extends RuntimeException {

    private String message;

    public AccountException(String msg) {
        this.message = msg;
    }
}
