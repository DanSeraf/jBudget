package it.unicam.cs.pa.jbudget097845.model.movement;

import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;

import java.util.List;

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

    public Movement newMovement(MovementType type, double amount, Transaction t, List<Tag> tags, Account acc) {
        Movement new_movement;
        switch (type) {
            case CREDIT:
                new_movement = new CreditMovement(amount, t, type);
                new_movement.setAccount(acc);
                tags.forEach(new_movement::addTag);
                return new_movement;
            case DEBIT:
                new_movement = new DebitMovement(amount, t, type);
                new_movement.setAccount(acc);
                tags.forEach(new_movement::addTag);
                return new_movement;
            default: return null;
        }
    }

    public Movement newMovement(MovementType type, double amount, Transaction t, List<Tag> tags, Account acc, String description) {
        Movement new_movement;
        switch (type) {
            case CREDIT:
                new_movement = new CreditMovement(amount, t, type);
                new_movement.setAccount(acc);
                new_movement.setDescription(description);
                tags.forEach(new_movement::addTag);
                return new_movement;
            case DEBIT:
                new_movement = new DebitMovement(amount, t, type);
                new_movement.setAccount(acc);
                new_movement.setDescription(description);
                tags.forEach(new_movement::addTag);
                return new_movement;
            default: return null;
        }
    }
}
