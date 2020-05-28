package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.transaction.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface Registry {
    List<Account> getAccounts();

    Account getAccount(Predicate<Account> predicate);

    void addTransaction(Transaction transaction);

    List<Transaction> getTransactions();

    List<Transaction> getTransactions(Predicate<Transaction> predicate);

    List<Tag> getTags();

    List<Tag> getTags(Predicate<Tag> p);

    void addAccount(AccountType type, String name, String description, double openingBalance);

    void addTag(String name, String description, TagType tag_type);

    void addScheduledTransaction(ScheduledTransaction scheduledTransaction);

    void schedule(LocalDate d);
}
