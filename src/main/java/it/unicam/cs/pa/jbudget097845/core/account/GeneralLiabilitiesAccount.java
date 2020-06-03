package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.exc.AccountBalanceError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GeneralLiabilitiesAccount implements Account {

    private final static AccountType type = AccountType.ASSETS;
    private final int id;
    private double openingBalance;
    private double balance;
    private String name;
    private String description;
    private List<Movement> movements = new ArrayList<>();

    public GeneralLiabilitiesAccount(int id, double openingBalance, String name, String description) {
        this.id = id;
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
    }

    @Override
    public void addMovement(Movement m) throws AccountBalanceError {
        if (m.type() == MovementType.DEBIT) {
            this.balance += m.amount();
        } else if (m.type() == MovementType.CREDIT) {
            if (this.balance < m.amount())
                throw new AccountBalanceError(String.format(
                        "Trying to remove amount '%.2f' on a limited account with balance '%.2f'",
                        m.amount(), this.balance));
            this.balance -= m.amount();
        }
        this.movements.add(m);
    }

    @Override
    public int getId() {
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
