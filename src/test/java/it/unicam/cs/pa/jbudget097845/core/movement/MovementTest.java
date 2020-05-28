package it.unicam.cs.pa.jbudget097845.core.movement;

import it.unicam.cs.pa.jbudget097845.core.GeneralTag;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.TagType;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovementTest {

    @Test
    @DisplayName("Create a new credit movement")
    public void createCreditMovement() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("Salary", "Salary income", TagType.INCOME));

        Transaction t = TransactionManager.newTransaction(tags);
        Movement m = MovementManager.newMovement(MovementType.CREDIT, 200, t, LocalDate.now(), tags);
        assertTrue(m instanceof CreditMovement);
    }

    @Test
    @DisplayName("Create a new debit movement")
    public void createDebitMovement() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("utilities", "utilities expenses", TagType.EXPENSE));

        Transaction t = TransactionManager.newTransaction(tags);
        Movement m = MovementManager.newMovement(MovementType.DEBIT, 200, t, LocalDate.now(), tags);
        assertTrue(m instanceof DebitMovement);
    }

}