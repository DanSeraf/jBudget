package it.unicam.cs.pa.jbudget097845.model.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that define a general transaction behavior
 *
 * @see Transaction
 */
@JsonTypeName("general_transaction")
public class GeneralTransaction implements Transaction {

    private List<Movement> movements = new ArrayList<>();
    @JsonIdentityReference(alwaysAsId = true)
    private List<Tag> tags = new ArrayList<>();
    private double totalAmount = 0;
    private LocalDate date;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GeneralTransaction(@JsonProperty("date") LocalDate d) {
        this.date = d;
    }

    private void addToTotal(Movement m) {
        if (m.getType() == MovementType.CREDIT
                && m.getAccount().getType() == AccountType.ASSETS)
            this.totalAmount += m.getAmount();
        else this.totalAmount -= m.getAmount();
    }

    private void removeFromTotal(Movement m) {
        if (m.getType() == MovementType.DEBIT
                && m.getAccount().getType() == AccountType.LIABILITIES)
            this.totalAmount += m.getAmount();
        else this.totalAmount -= m.getAmount();
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public void addMovement(Movement m) {
        addToTotal(m);
        tags.addAll(m.getTags());
        this.movements.add(m);
    }

    @Override
    public void deleteMovement(Movement m) {
        removeFromTotal(m);
        tags.removeAll(m.getTags());
        this.movements.remove(m);
    }

    @Override
    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public void addTag(Tag t) {
        this.tags.add(t);
    }

    @Override
    public void removeTag(Tag t) {
        this.tags.remove(t);
    }

    @Override
    public void setDate(LocalDate d) {
        this.date = d;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public double getTotalAmount() {
        return this.totalAmount;
    }
}
