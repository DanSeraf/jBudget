package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.ArrayList;
import java.util.List;

public class Bmanager implements BudgetManager {
    @Override
    public BudgetReport generateReport(Registry r, Budget b) {
        List<Movement> movements = new ArrayList<>();
        r.getTransactions()
                .forEach(t -> t.getMovements()
                        .forEach(movements::add));

        return new Breport(b, movements);
    }
}
