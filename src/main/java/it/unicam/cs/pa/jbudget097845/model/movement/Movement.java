package it.unicam.cs.pa.jbudget097845.model.movement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface implemented by the classes that should manage a single type of movement.
 * It is able to access/modify the information associated to a movement.
 *
 * @author Daniele Serafini
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope=Movement.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditMovement.class, name = "credit_movement"),
        @JsonSubTypes.Type(value = DebitMovement.class, name = "debit_movement")
})
public interface Movement {

    /**
     * @return the MovementType
     *
     * @see MovementType
     */
    MovementType getType();

    /**
     * @return the description of the movement
     */
    String getDescription();

    /**
     *
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @return the Transaction associated to the movement
     */
    Transaction getTransaction();

    /**
     * @return the amount of the movement
     */
    double getAmount();

    /**
     * @return the Account associated to the movement
     */
    Account getAccount();

    /**
     * @param a the account referenced to the movement
     */
    void setAccount(Account a);

    /**
     * @return the date of the movement
     */
    LocalDate getDate();

    /**
     * @return the tags associated to the movement
     */
    List<Tag> getTags();

    /**
     * @param t the tag to be added
     */
    void addTag(Tag t);

    /**
     * @param t the tab to be removed
     */
    void removeTag(Tag t);
}
