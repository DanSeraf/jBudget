package it.unicam.cs.pa.jbudget097845.model.budget;

import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    }

    @Override
    public List<Tag> tags() {
        return this.tags;
    }

    @Override
    public Map<Tag, Double> report() {
        Map<Tag, Double> reports = new HashMap<>();

        /**
         * @author Francesco Mecca (me@francescomecca.eu)
         */
        tags.forEach(tag -> {
            Double amount = movements.stream()
                    .filter(m -> m.getTags().contains(tag))
                    .map(m -> {
                        if (m.getType() == MovementType.CREDIT)
                            return m.getAmount();
                        else return - m.getAmount();
                    })
                    .reduce(0.0, (a, b) -> a + b);
            reports.put(tag, this.budget.get(tag) - amount);
        });
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
