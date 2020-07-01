package it.unicam.cs.pa.jbudget097845.model.movement;

import it.unicam.cs.pa.jbudget097845.model.GeneralTag;
import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.model.transaction.TransactionManager;
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
        TransactionManager transactionManager = TransactionManager.instance();
        MovementManager movementManager = MovementManager.instance();
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("Salary", "Salary income"));

        Transaction t = transactionManager.newTransaction(LocalDate.now());
        Movement m = movementManager.newMovement(MovementType.CREDIT, 200, t, tags);
        assertTrue(m instanceof CreditMovement);
    }

    @Test
    @DisplayName("Create a new debit movement")
    public void createDebitMovement() {
        TransactionManager transactionManager = TransactionManager.instance();
        MovementManager movementManager = MovementManager.instance();
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("utilities", "utilities expenses"));

        Transaction t = transactionManager.newTransaction(LocalDate.now());
        Movement m = movementManager.newMovement(MovementType.DEBIT, 200, t, tags);
        assertTrue(m instanceof DebitMovement);
    }

}