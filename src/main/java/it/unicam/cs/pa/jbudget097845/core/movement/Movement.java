package it.unicam.cs.pa.jbudget097845.core.movement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope=Movement.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditMovement.class, name = "credit_movement"),
        @JsonSubTypes.Type(value = DebitMovement.class, name = "debit_movement")
})
public interface Movement {
    MovementType getType();

    String getDescription();

    void setDescription(String description);

    Transaction getTransaction();

    double amount();

    Account getAccount();

    void setAccount(Account a);

    LocalDate getDate();

    List<Tag> getTags();

    void addTag(Tag t);

    void removeTag(Tag t);
}
