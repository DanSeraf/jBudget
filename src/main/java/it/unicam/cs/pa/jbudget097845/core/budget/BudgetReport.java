package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Interface implemented by the classes responsible of show the difference of gain/expense
 * against a specific Budget. It should created by a `BudgetManager`
 *
 * @see BudgetManager
 *
 */
public interface BudgetReport {

    /**
     * @return the tags associated to the report
     */
    List<Tag> tags();

    /**
     * @return an HashMap containing the difference of gain/expense for each Tag
     */
    Map<Tag, Double> report();

    /**
     * @return the `Budget` associated to the report
     */
    Budget getBudget();

    /**
     * @param t a `Tag`
     * @return the difference of gain/expense of the input Tag
     */
    double get(Tag t);

}
