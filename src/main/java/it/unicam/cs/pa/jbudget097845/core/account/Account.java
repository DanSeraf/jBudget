package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import java.util.List;
import java.util.function.Predicate;

public interface Account {
    int getId();

    String getName();

    String getDescription();

    double getOpeningBalance();

    double getBalance();

    void addMovement(Movement m);

    List<Movement> getMovements();

    List<Movement> getMovements(Predicate<Movement> predicate);
}
