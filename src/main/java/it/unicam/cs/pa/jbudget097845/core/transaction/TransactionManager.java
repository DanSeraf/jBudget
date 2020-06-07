package it.unicam.cs.pa.jbudget097845.core.transaction;

import it.unicam.cs.pa.jbudget097845.core.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is responsible of the management about new transactions
 */

public class TransactionManager {

    private TransactionManager class_instance = null;
    private static HashMap<String, Transaction> defaultTransactions = new HashMap<>();

    private TransactionManager() {}

    public TransactionManager TransactionManager() {
        if (this.class_instance == null) this.class_instance = new TransactionManager();
        return this.class_instance;
    }


    public static Transaction newTransaction() {
        Transaction t = new GeneralTransaction();
        return t;
    }

    public void saveTransaction(Transaction t, String name) {
        this.defaultTransactions.put(name, t);
    }
}
