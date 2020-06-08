package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.exc.BudgetNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class to manage the creation of a BudgetReport.
 * It also save the budget and the report
 *
 * @see BudgetManager
 *
 */
public class BudgetHandler implements BudgetManager {

    private List<Budget> budgets;

    @Override
    public BudgetReport generateReport(Registry r, Budget b) {

        List<Movement> movements = new ArrayList<>();
        r.getTransactions()
                .forEach(t -> movements.addAll(t.getMovements()));

        return new GeneralReport(b, movements);
    }

    public BudgetReport generateReport(Registry r, Predicate<Budget> p)
    throws BudgetNotFound
    {
        Budget b = this.budgets.stream()
                .filter(p)
                .findAny()
                .orElse(null);
        if (b != null) {
            List<Movement> movements = new ArrayList<>();
            r.getTransactions()
                    .forEach(t -> movements.addAll(t.getMovements()));

            return new GeneralReport(b, movements);
        } else throw new BudgetNotFound("Budget not found");
    }
}
