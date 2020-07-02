package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.exc.account.AccountBalanceError;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import it.unicam.cs.pa.jbudget097845.model.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.model.transaction.TransactionManager;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import java.time.LocalDate;
import java.util.*;

/**
 * This class is responsible of managing the communication between the API server
 * and the model (in this case the Ledger).
 * It will parse the messages from the API Endpoint to communicate with the Registry.
 * In case of a message failure the appropriate exception will be trown.
 *
 * @see it.unicam.cs.pa.jbudget097845.server.Endpoints
 * @see Registry
 *
 */
public class ApplicationController {

    private Registry registry;
    private TransactionManager transactionManager;
    private MovementManager movementManager;
    private final BudgetHandler budgetHandler;

    public ApplicationController(Registry r, BudgetHandler bh) {
        registry = r;
        budgetHandler = bh;
    }

    public ApplicationController(Registry r) {
        registry = r;
        budgetHandler = new BudgetHandler();
        transactionManager = TransactionManager.instance();
        movementManager = MovementManager.instance();

    }

    public ApplicationController() {
        registry = Ledger.instance();
        transactionManager = TransactionManager.instance();
        movementManager = MovementManager.instance();
        budgetHandler = new BudgetHandler();
    }

    /**
     *
     */
    public void generateAccount(String name, String description, AccountType account_type, double openingBalance, boolean belowZero)
    throws AccountCreationError
    {
        registry.addAccount(account_type, name, description, openingBalance, belowZero);
    }

    /**
     * It gets all the account from the Registry and
     *
     * @see Registry
     *
     * @return the formatted message containing all the accounts
     */
    public List<Account> getAccounts() {
        return registry.getAccounts();
    }

    public void newTransaction(
            List<Quintet<Double, MovementType, Account, List<Tag>, String>> movements
            ) throws AccountBalanceError
    {
        Transaction new_transaction = transactionManager.newTransaction(LocalDate.now());
        movements.forEach(mov -> {
            double amount = mov.getValue0();
            MovementType mov_type = mov.getValue1();
            Account acc = mov.getValue2();
            List<Tag> tags = mov.getValue3();
            String desc = mov.getValue4();
            Movement new_movement = movementManager.newMovement(mov_type, amount, new_transaction, tags, acc, desc);
            new_transaction.addMovement(new_movement);
        });

        registry.addTransaction(new_transaction);
    }

    public List<Transaction> getTransactions() {
        return registry.getTransactions();
    }

    public List<Tag> getTags() {
        return registry.getTags();
    }

    public void generateBudget() {

    }

    public void addTag(String name, String description) {
        registry.addTag(name, description);
    }
}
