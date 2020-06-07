package it.unicam.cs.pa.jbudget097845.core.account;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.core.movement.CreditMovement;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.exc.AccountBalanceError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GeneralAssetAccount implements Account {

    private final static AccountType type = AccountType.ASSETS;
    private final long id;
    private double openingBalance;
    private double balance;
    private String name;
    private String description;
    private List<Movement> movements = new ArrayList<>();
    private boolean belowZero;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GeneralAssetAccount(
            @JsonProperty("id") long id,
            @JsonProperty("opening_balance") double openingBalance,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("below_zero") boolean belowZero)
    {
        this.id = id;
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
        this.belowZero = belowZero;
    }

    public boolean getBelowZero() {
        return this.belowZero;
    }

    @Override
    public void addMovement(Movement m) throws AccountBalanceError {
        if (m.type() == MovementType.DEBIT) {
            if (this.balance < m.amount() && !belowZero)
                throw new AccountBalanceError(String.format(
                        "Trying to remove amount '%.2f' on account with balance '%.2f'", m.amount(), this.balance));
            this.balance -= m.amount();
        } else if (m.type() == MovementType.CREDIT) {
            this.balance += m.amount();
        }
        m.setAccount(this);
        this.movements.add(m);
    }

    @Override
    public long getId() {
        return this.id;
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
