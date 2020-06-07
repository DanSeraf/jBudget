package it.unicam.cs.pa.jbudget097845.core.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneralTransaction implements Transaction {

    private final long id;
    private List<Movement> movements = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private double totalAmount;
    private LocalDate date;

    @JsonCreator
    public GeneralTransaction(@JsonProperty("id") long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public void addMovement(Movement m) {
        addToTotal(m);
        this.movements.add(m);
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

    private void addToTotal(Movement m) {
        if (m.type() == MovementType.CREDIT) this.totalAmount += m.amount();
        else this.totalAmount -= m.amount();
    }

    @Override
    public double getTotalAmount() {
        return this.totalAmount;
    }
}
