package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetReport;
import it.unicam.cs.pa.jbudget097845.core.budget.GeneralReport;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.AccountTypeException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

/**
 * This class is responsible of managing the communication between the API server
 * and the model (in this case the Ledger).
 * It will parse the message field
 */

public class Controller {

    private Controller class_instance = null;
    private static Registry registry;
    private static BudgetHandler budgetHandler;
    private static Map<String, AccountType> accountTypes = new HashMap<>();
    private static Map<String, MovementType> movementTypes = new HashMap<>();

    private Controller() {
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

    public Controller Controller() {
        if (this.class_instance == null) {
            this.class_instance = new Controller();
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

    //TODO throw exception if account name already exists
    public static void generateAccount(String name, String description, String account_type, String openingBalance)
    throws AccountCreationError, AccountTypeException
    {
        double op_balance = Double.parseDouble(openingBalance);
        AccountType at = accountTypes.get(account_type);
        if (at == null) throw new AccountTypeException("The account type provided doesn't exists");
        registry.addAccount(at, name, description, op_balance);
    }

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

    public static void addTag(String name, String description) {
        registry.addTag(name, description);
    }

}
