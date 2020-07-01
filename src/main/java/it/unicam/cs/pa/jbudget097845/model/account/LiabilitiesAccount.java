package it.unicam.cs.pa.jbudget097845.model.account;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
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
            @JsonProperty("account_type") AccountType type
    )
    {
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    private void addDebit(Movement m) {
        this.balance += m.getAmount();
    }

    private void addCredit(Movement m) throws AccountBalanceError {
        if (this.balance < m.getAmount())
            throw new AccountBalanceError(String.format(
                    "Trying to remove amount '%.2f' on a limited account with balance '%.2f'",
                    m.getAmount(), this.balance));
    }

    @Override
    public void addMovement(Movement m) throws AccountBalanceError {
        if (m.getType() == MovementType.DEBIT) {
            addDebit(m);
        } else if (m.getType() == MovementType.CREDIT) {
            addCredit(m);
        }

        this.movements.add(m);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getOpeningBalance() {
        return this.openingBalance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

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

    @Override
    public void deleteMovements(Predicate<Movement> p) {
        getMovements(p).forEach(m -> {
            if (m.getType() == MovementType.CREDIT) addDebit(m);
            else addCredit(m);
            this.movements.remove(m);
        });
    }

    @Override
    public void deleteMovement(Movement m) {
        if (m.getType() == MovementType.CREDIT) addDebit(m);
        else addCredit(m);
        this.movements.remove(m);
    }
}
