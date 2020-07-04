package it.unicam.cs.pa.jbudget097845.model.budget;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.Registry;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface that implements class responsible of generate a new BudgetReport
 * from a `Registry` and a `Budget`
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= BudgetManager.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value= BudgetHandler.class, name="budget_handler")
)
public interface BudgetManager {

    BudgetReport generateReport(Registry r, Budget b);

    /**
     * @param r an instance of type `Registry`
     * @return the list containing all the budgetReport generated
     */
    List<BudgetReport> generateReports(Registry r);

    void addBudget(Budget budget);

    List<Budget> getBudgets();
}
