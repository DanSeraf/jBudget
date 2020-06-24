package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

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

    private static ApplicationController class_instance = null;
    private Registry registry;
    private BudgetHandler budgetHandler;
    private final MovementManager movementManager = MovementManager.instance();
    private final TransactionManager transactionManager = TransactionManager.instance();

    private ApplicationController() {
    }

    public static ApplicationController instance() {
        if (class_instance == null) {
            class_instance = new ApplicationController();
        }

        return class_instance;
    }

    public void init(Registry r, BudgetHandler bh) {
        registry = r;
        budgetHandler = bh;
    }

    public void init(Registry r) {
        registry = r;
        budgetHandler = new BudgetHandler();
    }

    public void init() {
        registry = new Ledger();
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
    //TODO throw exception if account name already exists
    public void generateAccount(String name, String description, String account_type, String openingBalance)
    throws AccountCreationError
    {
        double op_balance = Double.parseDouble(openingBalance);
        AccountType at = AccountType.of(account_type);
        registry.addAccount(at, name, description, op_balance);
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
            String account_name, Transaction t, String rawType, String rawAmount, List<String> tagNames) {

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
