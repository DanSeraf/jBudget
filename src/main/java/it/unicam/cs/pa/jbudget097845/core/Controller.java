package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

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
    private static Registry l = new Ledger();
    private static Map<String, AccountType> accountTypes = new HashMap<>();

    private Controller() {
        initAccountTypes();
    }

    private void initAccountTypes() {
        AccountType[] account_types = AccountType.values();

        for (AccountType at: account_types) {
            this.accountTypes.put(at.toString(), at);
        }
    }

    public Controller Controller() {
        if (this.class_instance == null) {
            this.class_instance = new Controller();
        }

        return this.class_instance;
    }

    public static void generateAccount(String name, String description, String account_type, String openingBalance)
    throws AccountCreationError {
        double op_balance = Double.parseDouble(openingBalance);
        AccountType at = accountTypes.get(account_type);
        l.addAccount(at, name, description, op_balance);
    }

    public static Map<String, Map<String, String>> getAccounts() {
        Map<String, Map<String, String>> accounts = new HashMap<>();
        for (Account acc: l.getAccounts()) {
            accounts.put(acc.getName(), new HashMap<String, String>() {{
                put("description", acc.getDescription());
                put("balance", Double.toString(acc.getBalance()));
                put("opening_balance", Double.toString(acc.getOpeningBalance()));
            }});
        }
        return accounts;
    }

    public static List<Account> accs() {
        return l.getAccounts();
    }
}
