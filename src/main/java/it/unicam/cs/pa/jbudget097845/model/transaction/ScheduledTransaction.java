package it.unicam.cs.pa.jbudget097845.model.transaction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface implemented by the classes that implement one of more transaction(s) in
 * specific date.
 *
 * When isCompleted is true it means that all the transactions are correcly added.
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= ScheduledTransaction.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value = ScheduledTransactionHandler.class, name = "scheduled_transaction")
)
public interface ScheduledTransaction {

    /**
     * @param transaction the transaction to be scheduled
     */
    void addTransaction(Transaction transaction);

    /**
     * @return the description of the scheduled transaction
     */
    String getDescription();

    /**
     * @param description the description of the scheduled transaction
     */
    void setDescription(String description);

    /**
     * @return all the scheduled transactions
     */
    List<Transaction> getTransactions();

    /**
     * @param p the predicate to filter the transactions
     * @return only the filtered transactions
     */
    List<Transaction> getTransactions(Predicate<Transaction> p);

    /**
     * @return all the transactions are completed
     */
    boolean isCompleted();
}
