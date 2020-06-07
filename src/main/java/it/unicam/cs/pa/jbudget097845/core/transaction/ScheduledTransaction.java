package it.unicam.cs.pa.jbudget097845.core.transaction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= ScheduledTransaction.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value = GeneralScheduledTransaction.class, name = "scheduled_transaction")
)
public interface ScheduledTransaction {

    void addTransaction(Transaction transaction);

    String getDescription();

    void setDescription(String description);

    List<Transaction> getTransactions();

    List<Transaction> getTransactions(Predicate<Transaction> p);

    boolean isCompleted();
}
