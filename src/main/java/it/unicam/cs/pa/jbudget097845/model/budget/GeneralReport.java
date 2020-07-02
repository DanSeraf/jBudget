package it.unicam.cs.pa.jbudget097845.model.budget;

import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that manage the creation of a new report (the gain/expense difference associated
 * at each Tag)
 *
 * @see BudgetReport
 *
 */
public class GeneralReport implements BudgetReport {

    private Budget budget;
    private List<Movement> movements;
    private List<Tag> tags;
    private Map<Tag, Double> reports;

    public GeneralReport(Budget b, List<Movement> m) {
        this.budget = b;
        this.tags = b.getTags();
        this.movements = m;
        this.reports = report();
    }

    @Override
    public List<Tag> tags() {
        return this.tags;
    }

    // TODO rewrite using stream
    @Override
    public Map<Tag, Double> report() {
        Map<Tag, Double> reports = new HashMap<>();
        for (Tag t: tags) {
            double[] tmp_amount = { 0 };
            movements.forEach((m) -> {
                if (m.getTags().contains(t)) {
                    tmp_amount[0] += m.getAmount();
                }
            });
            reports.put(t, this.budget.get(t) - tmp_amount[0]);
        };
        return reports;
    }

    @Override
    public Budget getBudget() {
        return this.budget;
    }

    @Override
    public double get(Tag t) {
        return this.reports.get(t);
    }
}
