package it.unicam.cs.pa.jbudget097845.exc.budget;

/**
 * Exception for budget not found
 */
public class BudgetNotFound extends RuntimeException {
    public BudgetNotFound(String message) {
        super(message);
    }
}
