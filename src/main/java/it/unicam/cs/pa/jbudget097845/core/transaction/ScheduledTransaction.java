package it.unicam.cs.pa.jbudget097845.core.transaction;

import java.util.List;

public interface ScheduledTransaction {

    void addTransaction(Transaction transaction);

    String getDescription();

    void setDescription(String description);

    List<Transaction> getTransactions();

    boolean isCompleted();
}
