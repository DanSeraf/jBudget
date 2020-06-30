package it.unicam.cs.pa.jbudget097845.exc.Transaction;

/**
 * Excetpion in case of transaction error
 */
public class TransactionError extends RuntimeException {
    public TransactionError(String message) {
        super(message);
    }
}
