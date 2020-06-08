package it.unicam.cs.pa.jbudget097845.exc;

/**
 * Exception for problems related to directory
 */
public class DirectoryError extends RuntimeException {
    public DirectoryError(String message) {
        super(message);
    }
}
