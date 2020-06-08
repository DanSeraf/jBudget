package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Registry;

/**
 * Interface that implements class responsible of generate a new BudgetReport
 * from a `Registry` and a `Budget`
 *
 */
public interface BudgetManager {

    /**
     * @param r an instance of type `Registry`
     * @param b an instance of type `Budget`
     * @return
     */
    BudgetReport generateReport(Registry r, Budget b);
}
