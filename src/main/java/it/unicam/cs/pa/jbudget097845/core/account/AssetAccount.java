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
 * This class is responsible of managing an account of type ASSET.
 * It provide the methods to add a new movement and should interact with
 * the Registry in order to register the transaction
 *
 * @see AccountType
 * @see Registry
 *
 */
@JsonTypeName("general_asset_account")
public class AssetAccount implements Account {

    private final AccountType type;
    @JsonIdentityReference(alwaysAsId = true)
    private Registry registry;
    private double openingBalance;
    private double balance;
    private String name;
    private String description;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Movement> movements = new ArrayList<>();
    private boolean belowZero;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AssetAccount(
            @JsonProperty("opening_balance") double openingBalance,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("account_type") AccountType type,
            @JsonProperty("below_zero") boolean belowZero,
            @JsonProperty("registry") Registry registry)
    {
        this.openingBalance = this.balance = openingBalance;
        this.name = name;
        this.description = description;
        this.belowZero = belowZero;
        this.type = type;
        this.registry = registry;
    }

    public boolean getBelowZero() {
        return this.belowZero;
    }

    /**
     * Action performed to the balance when the movement is a debit
     *
     * @param m the debit movement
     * @throws AccountBalanceError
     */
    private void addDebit(Movement m) throws AccountBalanceError {
        if (this.balance < m.amount() && !belowZero)
            throw new AccountBalanceError(String.format(
                    "Trying to remove amount '%.2f' on account with balance '%.2f'", m.amount(), this.balance));
        this.balance -= m.amount();
    }

    private void addCredit(Movement m) {
        this.balance += m.amount();
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

    /**
     * @return the movements associated to the account
     */
    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    /**
     * @param predicate predicate to filter movements
     * @return the filtered movements associated to the account
     */
    @Override
    public List<Movement> getMovements(Predicate<Movement> predicate) {
        return this.movements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
