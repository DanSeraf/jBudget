package it.unicam.cs.pa.jbudget097845;

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
import it.unicam.cs.pa.jbudget097845.model.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;

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

    private Registry registry = Ledger.instance();
    private BudgetHandler budgetHandler = new BudgetHandler();
    private final MovementManager movementManager = MovementManager.instance();
    private final TransactionManager transactionManager = TransactionManager.instance();

    public ApplicationController(Registry r, BudgetHandler bh) {
        registry = r;
        budgetHandler = bh;
    }

    public ApplicationController(Registry r) {
        registry = r;
    }

    public ApplicationController() {
        //registry = new Ledger();
        budgetHandler = new BudgetHandler();
    }

    /**
     * It will try to generate a new account parsing the input from the API Endpoint "/newaccount".
     *
     * @see it.unicam.cs.pa.jbudget097845.server.Endpoints
     * @see AccountType
     *
     * @param name the name of the account
     * @param description the description of the account
     * @param account_type the AccountType
     * @param openingBalance the opening balance of the account
     * @throws AccountCreationError in case of creation error
     */
    public void generateAccount(String name, String description, AccountType account_type, double openingBalance)
    throws AccountCreationError
    {
        registry.addAccount(account_type, name, description, openingBalance);
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
            Account account_name, Transaction t, String rawType, String rawAmount, List<String> tagNames) {

        List<Tag> tags = new ArrayList<>();
        tagNames.forEach(tag_name -> {
            tags.add(registry.getTag(tag -> Objects.equals(tag.getName(), tag_name)));
        });

        double amount = Double.parseDouble(rawAmount);
        MovementType movementType = MovementType.of(rawType);
        Movement new_m = movementManager.newMovement(
                movementType, amount, t, tags);
        Account account = registry.getAccount(a -> a.getName().equals(account_name));
        account.addMovement(new_m);
    }

    public void generateBudget() {

    }

    public void addTag(String name, String description) {
        registry.addTag(name, description);
    }
}