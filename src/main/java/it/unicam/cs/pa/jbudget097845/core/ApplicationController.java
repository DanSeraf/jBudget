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
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.AccountTypeException;
import org.json.JSONArray;

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

    private ApplicationController class_instance = null;
    private static Registry registry;
    private static BudgetHandler budgetHandler;
    private static Map<String, AccountType> accountTypes = new HashMap<>();
    private static Map<String, MovementType> movementTypes = new HashMap<>();

    private ApplicationController() {
    }

    private static void initAccountTypes() {
        AccountType[] account_types = AccountType.values();

        for (AccountType at: account_types) {
            accountTypes.put(at.toString(), at);
        }
    }

    private static void initMovementType() {
        MovementType[] movement_type = MovementType.values();

        for (MovementType mt: movement_type) {
            movementTypes.put(mt.toString(), mt);
        }
    }

    public ApplicationController Controller() {
        if (this.class_instance == null) {
            this.class_instance = new ApplicationController();
        }

        return this.class_instance;
    }

    public static void init(Registry r, BudgetHandler bh) {
        registry = r;
        budgetHandler = bh;

        initAccountTypes();
        initMovementType();
    }

    public static void init(Registry r) {
        registry = r;
        budgetHandler = new BudgetHandler();

        initAccountTypes();
        initMovementType();
    }

    public static void init() {
        registry = new Ledger();
        budgetHandler = new BudgetHandler();

        initAccountTypes();
        initMovementType();
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
     * @throws AccountTypeException in case the type of the account is wrong
     */
    //TODO throw exception if account name already exists
    public static void generateAccount(String name, String description, String account_type, String openingBalance)
    throws AccountCreationError, AccountTypeException
    {
        double op_balance = Double.parseDouble(openingBalance);
        AccountType at = accountTypes.get(account_type);
        if (at == null) throw new AccountTypeException("The account type provided doesn't exists");
        registry.addAccount(at, name, description, op_balance);
    }

    /**
     * It gets all the account from the Registry and
     *
     * @see Registry
     *
     * @return the formatted message containing all the accounts
     * @throws AccountNotFound
     */
    public static Map<String, Map<String, String>> getAccounts() throws AccountNotFound {
        Map<String, Map<String, String>> accounts = new HashMap<>();
        for (Account acc: registry.getAccounts()) {
            accounts.put(acc.getName(), new HashMap<String, String>() {{
                put("description", acc.getDescription());
                put("balance", Double.toString(acc.getBalance()));
                put("opening_balance", Double.toString(acc.getOpeningBalance()));
            }});
        }
        System.out.println(accounts);
        return accounts;
    }

    public static void generateTransaction(JSONArray movements) {
        Transaction t = TransactionManager.newTransaction();

        for (int i = 0; i < movements.length(); i++) {
            List<Tag> tags = new ArrayList<>();
            String account_name = movements.getJSONObject(i).getString("account_name");
            MovementType movement_type = movementTypes.get(movements.getJSONObject(i).getString("movement_type"));
            double amount = Double.parseDouble(movements.getJSONObject(i).getString("amount"));
            JSONArray tags_array = movements.getJSONObject(i).getJSONArray("tags");
            for (int j = 0; j < tags_array.length(); j++) {
                 String tag_name = tags_array.getString(i);
                 tags.add(registry.getTag(tag -> Objects.equals(tag.getName(), tag_name)));
            }
            Movement new_m = MovementManager.newMovement(
                    movement_type, amount, t, LocalDate.now(),tags);
            Account account = registry.getAccount(a -> a.getName().equals(account_name));
            account.addMovement(new_m);
        }
        registry.addTransaction(t);
    }

    public static void generateBudget() {

    }

    public static void addTag(String name, String description) {
        registry.addTag(name, description);
    }

}
