package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountFactory;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.transaction.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.TransactionError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ledger implements Registry {

    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<ScheduledTransaction> scheduledTransactions = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private AccountFactory accountManager = new AccountFactory();

    public Ledger() {
        addAccount(AccountType.ASSETS, "Banca", "Banca Unicredit", 10000);
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
    public void addTransaction(Transaction transaction) throws TransactionError {
        if (transaction.movements().size() == 0) throw new TransactionError(
                "Can't add a new transaction without movements");

        transactions.add(transaction);
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
    public void addAccount(AccountType type, String name, String description, double openingBalance)
    throws AccountCreationError {
        Account new_account = accountManager.newAccount(type, name, description, openingBalance);
        this.accounts.add(new_account);
    }

    @Override
    public void addTag(String name, String description, TagType tag_type) {
        Tag tag = new GeneralTag(name, description, tag_type);
        tags.add(tag);
    }

    @Override
    public void addScheduledTransaction(ScheduledTransaction st) {
        this.scheduledTransactions.add(st);
    }

    @Override
    public void schedule(LocalDate d) {
    }
}
