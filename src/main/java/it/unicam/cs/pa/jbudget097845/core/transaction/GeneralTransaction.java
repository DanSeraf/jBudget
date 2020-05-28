package it.unicam.cs.pa.jbudget097845.core.transaction;

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
    private LocalDate date;

    public GeneralTransaction(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public List<Movement> movements() {
        return this.movements;
    }

    @Override
    public void addMovement(Movement m) {
        this.movements.add(m);
    }

    @Override
    public List<Tag> tags() {
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
        double total = 0.0;
        for (Movement m: movements) {
            if (m.type() == MovementType.CREDIT) total += m.amount();
            else total -= m.amount();
        }
        return total;
    }
}
