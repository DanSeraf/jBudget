package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Registry;

public interface BudgetManager {
    BudgetReport generateReport(Registry r, Budget b);
}
