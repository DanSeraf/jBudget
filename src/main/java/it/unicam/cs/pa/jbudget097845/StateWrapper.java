package it.unicam.cs.pa.jbudget097845;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.model.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.model.budget.BudgetManager;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * This class is responsible of storing the state of the various application
 * data that need to be stored. Since the data is serialized in a unique file
 * this class provide a simple container for the classes that need to be serialized.
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= StateWrapper.class)
public class StateWrapper {
    private static StateWrapper instance = new StateWrapper();
    private Registry registry;
    private BudgetManager budgetManager;

    private StateWrapper() {
        this.registry = Ledger.instance();
        this.budgetManager = BudgetHandler.instance();
    }

    public static StateWrapper instance() {
        return instance;
    }

    public static void setInstance(StateWrapper sw) {
        instance = sw;
    }

    public void loadInstances() {
        Ledger.setInstance((Ledger) registry);
        BudgetHandler.setInstance((BudgetHandler) budgetManager);
    }
}
