package it.unicam.cs.pa.jbudget097845.core.movement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("credit_movement")
public class CreditMovement implements Movement {

    private static final MovementType type = MovementType.CREDIT;
    private long id;
    private double amount;
    private Account account = null;
    private String description = "";
    private Transaction transaction;
    private LocalDate date;
    private List<Tag> tags = new ArrayList<>();

    public CreditMovement() {}

    public CreditMovement(long id, double amount, Transaction t, LocalDate d) {
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
        //if (this.account == null) throw new AccountNotFound("The account should be added first");
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
    public List<Tag> getTags() {
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
