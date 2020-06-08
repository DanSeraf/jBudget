package it.unicam.cs.pa.jbudget097845.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountFactory;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.transaction.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that manage the behavior of the application data
 *
 * It is Serializable because it is the main class used by the Application State
 * to save all the application data.
 */
public class Ledger implements Registry, Serializable {

    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<ScheduledTransaction> scheduledTransactions = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    @JsonIgnore
    private AccountFactory accountManager = new AccountFactory();

    public Ledger() {}

    @Override
    public List<Account> getAccounts() throws AccountNotFound {
        if (this.accounts.size() == 0) throw new AccountNotFound("No accounts added");
        return this.accounts;
    }

    @Override
    public Account getAccount(Predicate<Account> p) throws AccountNotFound {
        Account acc = this.accounts.stream()
                .filter(p)
                .findAny()
                .orElse(null);
        if (acc == null)
            throw new AccountNotFound(String.format("The request account is not found"));
        else return acc;
    }

    @Override
    public void addTransaction(Transaction transaction) throws TransactionError {
        if (this.transactions.contains(transaction)) return;
        transactions.add(transaction);
        ApplicationState.save(this);
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public List<Transaction> getTransactions(Predicate<Transaction> p) {
        return this.transactions.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> getTags() {
        return this.tags;
    }

    @Override
    public List<Tag> getTags(Predicate<Tag> p) {
        return this.tags.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public Tag getTag(Predicate<Tag> p) {
        System.out.println(tags);
        Tag tag = this.tags.stream()
                .filter(p)
                .findAny()
                .orElse(null);
        if (tag == null)
            throw new TagNotFound("The request account is not found");
        else return tag;
    }

    @Override
    public void addAccount(AccountType type, String name, String description, double openingBalance)
    throws AccountCreationError {
        Account new_account = accountManager.newAccount(type, name, description, openingBalance, this);
        this.accounts.add(new_account);
        ApplicationState.save(this);
    }

    @Override
    public void addTag(String name, String description) {
        Tag tag = new GeneralTag(name, description);
        tags.add(tag);
        ApplicationState.save(this);
    }

    @Override
    public void addScheduledTransaction(ScheduledTransaction st) {
        this.scheduledTransactions.add(st);
    }

    @Override
    public void schedule(LocalDate d, List<Transaction> transacions) {
    }
}
