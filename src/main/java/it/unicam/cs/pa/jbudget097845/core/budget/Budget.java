package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Tag;

import java.util.List;

/**
 * Interface that provides the main methods to implement a new `Budget`.
 * The idea is to associate an amount to each tag that show the expense/gain for
 * the specific `Tag`
 *
 * @see it.unicam.cs.pa.jbudget097845.core.Tag
 *
 */
public interface Budget {

    /**
     * @return the tags associated to the Budget
     */
    List<Tag> getTags();

    /**
     * @param t the Tag to add
     * @param expected the gain/expense expected for the Tag
     */
    void set(Tag t, double expected);

    /**
     * @param t a Tag
     * @return the gain/expense expected for the Tag
     */
    double get(Tag t);
}
