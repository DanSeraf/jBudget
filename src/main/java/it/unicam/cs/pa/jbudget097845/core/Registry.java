package it.unicam.cs.pa.jbudget097845.core;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.transaction.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface implemented by the classes that will manage the application data.
 * The implemented class will be responsible of creating new accounts, adding/removing
 * new transactions, adding/removing tags and scheduling transaction in a certain date.
 *
 * Since the implemented class will contains all the application data, it should also
 * be responsible of saving the data with the provided ApplicationState class.
 *
 * @see ScheduledTransaction
 * @see ApplicationState
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Registry.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value=Ledger.class, name="ledger")
)
public interface Registry {
    /**
     * @return all the accounts
     */
    List<Account> getAccounts();

    /**
     * @param predicate predicate to filter accounts
     * @return a specific account
     */
    Account getAccount(Predicate<Account> predicate);

    /**
     * @param transaction add the transaction to the list of transactions
     */
    void addTransaction(Transaction transaction);

    /**
     * @return all the transactions
     */
    List<Transaction> getTransactions();

    /**
     * @param predicate prendicate to filter the transactions
     * @return the list of filtered transactions
     */
    List<Transaction> getTransactions(Predicate<Transaction> predicate);

    /**
     * @return all the tags available
     */
    List<Tag> getTags();

    /**
     * @param p predicate to filter the tags
     * @return the list of the filtered tags
     */
    List<Tag> getTags(Predicate<Tag> p);

    /**
     * @param p predicate to filter the tags
     * @return the first tag that match the predicate criteria
     */
    Tag getTag(Predicate<Tag> p);

    /**
     * Generate a new account
     *
     * @see AccountType
     *
     * @param type the AccountType of the account
     * @param name the name of the account
     * @param description the description of the account
     * @param openingBalance the opening balance of the account
     */
    void addAccount(AccountType type, String name, String description, double openingBalance);

    /**
     * Add a tag to the list of available tags
     *
     * @param name the name of the tag
     * @param description the description of the tag
     */
    void addTag(String name, String description);

    /**
     * @param scheduledTransaction the scheduled transaction to be added
     */
    void addScheduledTransaction(ScheduledTransaction scheduledTransaction);

    /**
     * generate a scheduled transaction from the input transactions and the input date
     *
     * @param d the date the transactions have to be scheduled
     * @param transactions the transactions to be scheduled
     */
    void schedule(LocalDate d, List<Transaction> transactions);
}
