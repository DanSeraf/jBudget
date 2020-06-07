package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetReport;
import it.unicam.cs.pa.jbudget097845.core.budget.GeneralReport;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.AccountTypeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Controller() { }

    private static void initAccountTypes() {
        AccountType[] account_types = AccountType.values();

        for (AccountType at: account_types) {
            accountTypes.put(at.toString(), at);
        }
    }

    public static Registry getRegistry() {
        return registry;
    }

    public Controller Controller() {
        if (this.class_instance == null) {
            this.class_instance = new Controller();
        }

        return this.class_instance;
    }

    public static void init(Registry r, BudgetHandler bh) {
        initAccountTypes();
        registry = r;
        budgetHandler = bh;
    }

    public static void init() {
        initAccountTypes();
        registry = new Ledger();
        budgetHandler = new BudgetHandler();
    }

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

    public static List<Account> accs() {
        return registry.getAccounts();
    }
}
