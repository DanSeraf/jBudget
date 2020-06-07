package it.unicam.cs.pa.jbudget097845.core.movement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("debit_movement")
public class DebitMovement implements Movement {

    private final MovementType type;
    private final double amount;
    private Account account = null;
    private String description = "";
    private final Transaction transaction;
    private final LocalDate date;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Tag> tags = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DebitMovement(
            @JsonProperty("amount") double amount,
            @JsonProperty("transaction") Transaction t,
            @JsonProperty("date") LocalDate d,
            @JsonProperty("type") MovementType mt)
    {
        this.amount = amount;
        this.transaction = t;
        this.date = d;
        this.type = mt;
    }

    @Override
    public MovementType getType() {
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
