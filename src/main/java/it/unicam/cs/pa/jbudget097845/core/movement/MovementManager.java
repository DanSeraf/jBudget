package it.unicam.cs.pa.jbudget097845.core.movement;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class responsible of managing the creation of new movements
 *
 */
public class MovementManager {

    private static MovementManager class_instance = null;

    private MovementManager() {}

    public static MovementManager instance() {
        if (class_instance == null) {
            class_instance = new MovementManager();
        }
        return class_instance;
    }

    public Movement newMovement(MovementType type, double amount, Transaction t, List<Tag> tags) {
        Movement new_movement;
        switch (type) {
            case CREDIT:
                new_movement = new CreditMovement(amount, t, type);
                tags.forEach(new_movement::addTag);
                return new_movement;
            case DEBIT:
                new_movement = new DebitMovement(amount, t, type);
                tags.forEach(new_movement::addTag);
                return new_movement;
            default: return null;
        }
    }
}
