package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Breport implements BudgetReport {

    private Budget budget;
    private List<Movement> movements;
    private List<Tag> tags;
    private Map<Tag, Double> reports;

    public Breport(Budget b, List<Movement> m) {
        this.budget = b;
        this.tags = b.tags();
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
                if (m.tags().contains(t)) {
                    tmp_amount[0] += m.amount();
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

    private Predicate<Movement> getPredicate(Tag t) {
        return m -> m.tags().contains(t);
    }
}
