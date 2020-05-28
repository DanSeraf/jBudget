package it.unicam.cs.pa.jbudget097845.core.transaction;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import java.time.LocalDate;
import java.util.List;

public interface Transaction {
    long getId();

    List<Movement> movements();

    void addMovement(Movement m);

    List<Tag> tags();

    void addTag(Tag tag);

    void removeTag(Tag tag);

    LocalDate getDate();

    void setDate(LocalDate d);

    double getTotalAmount();
}
