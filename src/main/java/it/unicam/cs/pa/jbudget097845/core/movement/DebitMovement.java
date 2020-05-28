package it.unicam.cs.pa.jbudget097845.core.movement;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DebitMovement implements Movement {

    private static final MovementType type = MovementType.DEBIT;
    private final long id;
    private double amount;
    private Account account = null;
    private String description = "";
    private Transaction transaction;
    private LocalDate date;
    private List<Tag> tags = new ArrayList<>();

    public DebitMovement(long id, double amount, Transaction t, LocalDate d) {
        this.id = id;
        this.amount = amount;
        this.transaction = t;
        this.date = d;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public MovementType type() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Transaction getTransaction() {
        return this.transaction;
    }

    @Override
    public double amount() {
        return this.amount;
    }

    @Override
    public Account getAccount() throws AccountNotFound {
        if (this.account == null) throw new AccountNotFound("The account should be added first");
        return this.account;
    }

    @Override
    public void setAccount(Account a) {
        this.account = a;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public List<Tag> tags() {
        return this.tags;
    }

    @Override
    public void addTag(Tag t) {
        this.tags.add(t);
    }

    @Override
    public void removeTag(Tag t) {
        this.tags.remove(t);
    }
}
