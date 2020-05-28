package it.unicam.cs.pa.jbudget097845.core.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneralScheduledTransaction implements ScheduledTransaction {

    private String description;
    private boolean completed = false;
    private List<Transaction> transactions = new ArrayList<>();
    private LocalDate date;

    public GeneralScheduledTransaction(String description, LocalDate d) {
        this.description = description;
        this.date = d;
    }

    @Override
    public void addTransaction(Transaction t) {
        this.transactions.add(t);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String d) {
        this.description = d;
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
