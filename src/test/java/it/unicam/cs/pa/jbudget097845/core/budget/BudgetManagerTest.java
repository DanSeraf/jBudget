package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.*;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetManagerTest {

    @Test
    @DisplayName("Creating new Budget Report")
    public void createBudgetReport() {
        Registry l = new Ledger();
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("Utilities", "Utilities expense", TagType.EXPENSE));

        Budget b = new GeneralBudget();
        b.set(tags.get(0), 1000);

        Transaction t = TransactionManager.newTransaction(tags);
        Transaction t2 = TransactionManager.newTransaction(tags);
        Movement m = MovementManager.newMovement(MovementType.DEBIT, 200, t, LocalDate.now(), tags);
        Movement m2 = MovementManager.newMovement(MovementType.DEBIT, 20, t, LocalDate.now(), tags);
        t.addMovement(m);
        t2.addMovement(m2);
        l.addTransaction(t);
        l.addTransaction(t2);

        BudgetManager bm = new BudgetFactory();
        BudgetReport br = bm.generateReport(l, b);
        Map<Tag, Double> reports = br.report();
        assertEquals(780, reports.get(tags.get(0)));
    }

}