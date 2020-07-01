package it.unicam.cs.pa.jbudget097845.model.budget;

import it.unicam.cs.pa.jbudget097845.model.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that store the expected values for each tag
 *
 * @see Budget
 */
public class GeneralBudget implements Budget {

    private HashMap<Tag, Double> budgets = new HashMap<>();

    @Override
    public List<Tag> getTags() {
        return new ArrayList<>(this.budgets.keySet());
    }

    @Override
    public void set(Tag t, double expected) {
        budgets.put(t, expected);
    }

    @Override
    public double get(Tag t) {
        return budgets.get(t);
    }
}
