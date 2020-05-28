package it.unicam.cs.pa.jbudget097845.core.transaction;

import it.unicam.cs.pa.jbudget097845.core.GeneralTag;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.TagType;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    @DisplayName("Add a movement to transaction")
    public void addMovement() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("Sport", "Sport transactions", TagType.EXPENSE));
        Transaction t = TransactionManager.newTransaction(tags);
        LocalDate date = LocalDate.now();
        Movement m = MovementManager.newMovement(MovementType.DEBIT, 10, t, date, tags);
        t.addMovement(m);

        List<Movement> movements = t.movements();
        for (Movement mov: movements) {
            assertEquals(MovementType.DEBIT, mov.type());
            assertEquals(10, mov.amount());
            assertEquals(date, mov.getDate());
        }

    }
}