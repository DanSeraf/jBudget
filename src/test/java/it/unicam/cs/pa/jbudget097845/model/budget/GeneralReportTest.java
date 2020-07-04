package it.unicam.cs.pa.jbudget097845.model.budget;

import it.unicam.cs.pa.jbudget097845.model.Tag.GeneralTag;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import it.unicam.cs.pa.jbudget097845.model.movement.*;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.model.transaction.TransactionManager;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralReportTest {

    @Test
    public void report() {
        Transaction t = TransactionManager.instance().newTransaction(LocalDate.now());
        MovementManager movementManager = MovementManager.instance();
        List<Movement> movements = new ArrayList<>();
        Tag tag = new GeneralTag("fatturato", "taaaac");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        Movement m = movementManager.newMovement(MovementType.CREDIT, 1000, t, tags);
        Movement m2 = movementManager.newMovement(MovementType.DEBIT, 100, t, tags);
        movements.add(m);
        movements.add(m2);
        Budget b = new GeneralBudget();
        b.set(tag, 2000);

        BudgetReport rep = new GeneralReport(b, movements);
        Map<Tag, Double> report = rep.report();
        assertEquals(1100, report.get(tag));
    }
}