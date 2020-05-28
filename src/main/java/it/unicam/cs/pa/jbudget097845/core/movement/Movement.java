package it.unicam.cs.pa.jbudget097845.core.movement;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface Movement {
    long getId();

    MovementType type();

    String getDescription();

    void setDescription(String description);

    Transaction getTransaction();

    double amount();

    Account getAccount();

    void setAccount(Account a);

    LocalDate getDate();

    List<Tag> tags();

    void addTag(Tag t);

    void removeTag(Tag t);
}
