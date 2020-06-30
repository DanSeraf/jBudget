package it.unicam.cs.pa.jbudget097845.core.account;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountBalanceError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is responsible of managing an account of type LIABILITIES.
 * It provide the methods to add a new movement and should interact with
 * the Registry in order to register the transaction
 * @see AccountType
 * @see Registry
 *
 */
@JsonTypeName("general_liabilites_account")
public class LiabilitiesAccount implements Account {

    private final AccountType type;
    @JsonIdentityReference(alwaysAsId = true)
    private Registry registry;
    private double openingBalance;
    private double balance;
    private String name;
    private String description;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Movement> movements = new ArrayList<>();

    @JsonCreator
    public LiabilitiesAccount(
            @JsonProperty("opening_balance") double openingBalance,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("account_type") AccountType type,
            @JsonProperty("registry") Registry registry)
    {
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
        this.type = type;
        this.registry = registry;
    }

    private void addDebit(Movement m) {
        this.balance += m.amount();
    }

    private void addCredit(Movement m) throws AccountBalanceError {
        if (this.balance < m.amount())
            throw new AccountBalanceError(String.format(
                    "Trying to remove amount '%.2f' on a limited account with balance '%.2f'",
                    m.amount(), this.balance));
    }

    @Override
    public void addMovement(Movement m) throws AccountBalanceError {
        if (m.getType() == MovementType.DEBIT) {
            addDebit(m);
        } else if (m.getType() == MovementType.CREDIT) {
            addCredit(m);
        }
        m.setAccount(this);
        this.movements.add(m);
        registry.addTransaction(m.getTransaction());
    }

    /**
     * @return the name of the account
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the description of the account
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the opening balance of the account
     */
    @Override
    public double getOpeningBalance() {
        return this.openingBalance;
    }

    /**
     * @return the current balance of the account
     */
    @Override
    public double getBalance() {
        return this.balance;
    }

    /**
     * @return the type of the account
     * @see it.unicam.cs.pa.jbudget097845.core.account.AccountType
     */
    @Override
    public AccountType getType() {
        return this.type;
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public List<Movement> getMovements(Predicate<Movement> predicate) {
        return this.movements.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
    }
}
