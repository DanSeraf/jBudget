package it.unicam.cs.pa.jbudget097845.core.transaction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.movement.CreditMovement;
import it.unicam.cs.pa.jbudget097845.core.movement.DebitMovement;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface implemented by the classes that should manage a Transaction.
 * It should provides the method to access/modify a transaction.
 *
 * Each Transaction should contain a totalAmount field that indicates the difference
 * of amounts based on the movements.
 *
 * When a movement is deleted/added to a transaction it must delete/add all the tags
 * associated to it and patch the totalAmount field.
 *
 * @see Transaction
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Transaction.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value = GeneralTransaction.class, name = "general_transaction")
)
public interface Transaction {

    /**
     * @return the movements associated to the transaction
     */
    List<Movement> getMovements();

    /**
     * It must add all the tags associated to the movement
     * and adjust the totalAmount field.
     *
     * @param m the movement to be added
     */
    void addMovement(Movement m);

    /**
     * It must delete all the tags associated to the movement
     * and adjust the totalAmount field.
     *
     * @param m the movement to be deleted
     */
    void deleteMovement(Movement m);

    /**
     * @return the tags associated to the transaction
     * @see Tag
     */
    List<Tag> getTags();

    /**
     * @param tag the tag to be added
     * @see Tag
     */
    void addTag(Tag tag);

    /**
     * @param tag the Tag to be removed
     * @see Tag
     */
    void removeTag(Tag tag);

    /**
     * @return the date of the transaction
     */
    LocalDate getDate();

    /**
     * @param d the date to be added to the transaction
     */
    void setDate(LocalDate d);

    double getTotalAmount();
}
