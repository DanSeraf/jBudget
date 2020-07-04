package it.unicam.cs.pa.jbudget097845.model.budget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.exc.BudgetNotFound;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class to manage the creation of a BudgetReport.
 * It also save the budgets and the reports
 *
 * @see BudgetManager
 *
 */
public class BudgetHandler implements BudgetManager {

    private static BudgetHandler instance = null;
    @JsonIgnore
    private ApplicationState state = new ApplicationState();
    private List<Budget> budgets = new ArrayList<>();

    private BudgetHandler() {};

    public static BudgetHandler instance() {
        if (instance == null)
            instance = new BudgetHandler();
        return instance;
    }

    public static void setInstance(BudgetHandler bh) {
        instance = bh;
    }

    @Override
    public BudgetReport generateReport(Registry r, Budget b) {

        List<Movement> movements = r.getTransactions().stream()
                .map(Transaction::getMovements)
                .flatMap(m -> m.stream())
                .filter(m -> m.getTags().containsAll(b.getTags()))
                .collect(Collectors.toList());

        return new GeneralReport(b, movements);
    }
    @Override

    public List<BudgetReport> generateReports(Registry r) {
        List<BudgetReport> budgetReports = new ArrayList<>();
        budgets.forEach(b -> budgetReports.add(generateReport(r, b)));
        return budgetReports;
    }

    /**
     * This method generate a report for a specific budget
     *
     * @param r the Registry where to take the transaction
     * @param p a predicate to filter the budgets
     * @return
     * @throws BudgetNotFound
     */
    public List<BudgetReport> generateReport(Registry r, Predicate<Budget> p)
    throws BudgetNotFound
    {
        List<Budget> filteredBudgets = this.budgets.stream()
                .filter(p)
                .collect(Collectors.toList());

        if (!filteredBudgets.isEmpty()) {
            return generateReports(r, budgets);
        } else throw new BudgetNotFound("No budget found with the given predicate");
    }

    /**
     *  Generate a list of BudgetReport from a defined list of budgets
     *
     * @param r an instance of Registry
     * @param budgets a list of budgets
     * @return a list with the BudgetReport generated
     */
    public List<BudgetReport> generateReports(Registry r, List<Budget> budgets) {
        List<BudgetReport> budgetReports = new ArrayList<>();
        budgets.forEach(b -> budgetReports.add(generateReport(r, b)));
        return budgetReports;
    }

    /**
     * Store a Budget
     *
     * @param b the budget to store
     */
    @Override
    public void addBudget(Budget b) {
        this.budgets.add(b);
        state.save();
    }

    /**
     * @return all the stored budgets
     */
    @Override
    public List<Budget> getBudgets() {
        return this.budgets;
    }
}
