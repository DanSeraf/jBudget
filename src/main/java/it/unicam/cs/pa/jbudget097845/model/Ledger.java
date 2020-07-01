package it.unicam.cs.pa.jbudget097845.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.account.AccountFactory;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.transaction.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.Transaction.TransactionError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountBalanceError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.tag.TagException;
import it.unicam.cs.pa.jbudget097845.exc.tag.TagNotFound;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that manage the behavior of the application data
 * TODO DEPENDENCY INJECTION
 */
public class Ledger implements Registry, Serializable {

    private static Ledger class_instance = null;
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<ScheduledTransaction> scheduledTransactions = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    @JsonIgnore
    private final AccountFactory accountManager = new AccountFactory();
    @JsonIgnore
    private final ApplicationState state = ApplicationState.instance();

    private Ledger() {}

    public static Ledger instance() {
        if (class_instance == null) {
            class_instance = new Ledger();
        }
        return class_instance;
    }

    public static void setInstance(Ledger l) {
        class_instance = l;
    }

    @Override
    public List<Account> getAccounts() {
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
    public void addTransaction(Transaction transaction) {
        if (this.transactions.contains(transaction))
            throw new TransactionError("Transaction already exists");

        for (Movement m: transaction.getMovements()) {
            Account acc = m.getAccount();
            try {
                acc.addMovement(m);
            } catch (AccountBalanceError abe) {
                // If there are problems with the account balance, delete all previous
                // movements in order to restore the previous state
                acc.deleteMovements(mov -> mov.getTransaction() == transaction);
                throw abe;
            }
        }

        transactions.add(transaction);
        state.save(this);
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
        if (getAccount(a -> a.getName().equalsIgnoreCase(name)) != null)
            throw new AccountCreationError("Account already exists");
        Account new_account = accountManager.newAccount(type, name, description, openingBalance, this);
        this.accounts.add(new_account);
        state.save(this);
    }

    @Override
    public void addTag(String name, String description) {
        tags.forEach((tag) -> {
            if (tag.getName().equalsIgnoreCase(name))
                throw new TagException("Tag already exists");
        });
        Tag tag = new GeneralTag(name, description);
        tags.add(tag);
        state.save(this);
    }

    @Override
    public void addScheduledTransaction(ScheduledTransaction st) {
        this.scheduledTransactions.add(st);
    }

    @Override
    public void schedule(LocalDate d, List<Transaction> transacions) {
    }
}
