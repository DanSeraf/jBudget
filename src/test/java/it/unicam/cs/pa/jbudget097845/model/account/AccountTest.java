package it.unicam.cs.pa.jbudget097845.model.account;

import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.model.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    @DisplayName("Account creation error while creating an account that can't go under the zero")
    public void createAccountError() {
        assertThrows(AccountCreationError.class, () -> AccountFactory.newAccount(
                AccountType.LIABILITIES, "Loan", "Loan expenses Account", 10000, Ledger.instance(), true));
    }

    @Test
    @DisplayName("Account add a new movement")
    public void addMovement() {
        MovementManager movementManager = MovementManager.instance();
        TransactionManager transactionManager = TransactionManager.instance();
        Registry r = Ledger.instance();
        r.addTag("rent", "rent gain");

        Account account = AccountFactory.newAccount(
                AccountType.ASSETS, "Bank", "Bank Account", 10000, r);

        Transaction new_transaction = transactionManager.newTransaction(LocalDate.now());
        Movement new_movement = movementManager.newMovement(
                MovementType.CREDIT, 100, new_transaction, r.getTags());

        account.addMovement(new_movement);

        assertEquals(1, r.getTransactions().size());
    }
}