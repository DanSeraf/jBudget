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

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Transaction.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value = GeneralTransaction.class, name = "general_transaction")
)
public interface Transaction {
    List<Movement> getMovements();

    void addMovement(Movement m);

    List<Tag> getTags();

    void addTag(Tag tag);

    void removeTag(Tag tag);

    LocalDate getDate();

    void setDate(LocalDate d);

    double getTotalAmount();
}
