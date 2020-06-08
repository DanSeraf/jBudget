package it.unicam.cs.pa.jbudget097845.core.transaction;

import java.time.LocalDate;

/**
 * This class is responsible manage the creation of new transactions
 */
public class TransactionManager {

    private static TransactionManager class_instance = null;

    private TransactionManager() {}

    public static TransactionManager instance() {
        if (class_instance == null) class_instance = new TransactionManager();
        return class_instance;
    }

    public Transaction newTransaction(LocalDate d) {
        Transaction t = new GeneralTransaction(d);
        return t;
    }
}
