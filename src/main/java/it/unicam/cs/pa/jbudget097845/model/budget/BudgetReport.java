package it.unicam.cs.pa.jbudget097845.model.budget;

import it.unicam.cs.pa.jbudget097845.model.Tag;

import java.util.List;
import java.util.Map;

/**
 * Interface implemented by the classes responsible of show the difference of gain/expense
 * against a specific Budget. It should created by the classes that implements the `BudgetManager`
 * interface.
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
