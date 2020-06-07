package it.unicam.cs.pa.jbudget097845.core.account;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.exc.AccountBalanceError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@JsonTypeName("general_liabilites_account")
public class GeneralLiabilitiesAccount implements Account {

    private final AccountType type;
    private double openingBalance;
    private double balance;
    private String name;
    private String description;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Movement> movements = new ArrayList<>();

    @JsonCreator
    public GeneralLiabilitiesAccount(
            @JsonProperty("opening_balance") double openingBalance,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("account_type") AccountType type)
    {
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public void addMovement(Movement m) throws AccountBalanceError {
        if (m.getType() == MovementType.DEBIT) {
            this.balance += m.amount();
        } else if (m.getType() == MovementType.CREDIT) {
            if (this.balance < m.amount())
                throw new AccountBalanceError(String.format(
                        "Trying to remove amount '%.2f' on a limited account with balance '%.2f'",
                        m.amount(), this.balance));
            this.balance -= m.amount();
        }
        m.setAccount(this);
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
}
