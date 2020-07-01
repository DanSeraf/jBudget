package it.unicam.cs.pa.jbudget097845.core.account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountBalanceError;

import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 *
 * Interface implemented by the classes that should implement a an `Account`
 * It provides the methods to modify and access the information of a specific Account
 *
 */

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Account.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AssetAccount.class, name = "general_asset_account"),
        @JsonSubTypes.Type(value = LiabilitiesAccount.class, name = "general_liabilities_account")
})
public interface Account {

    /**
     * @return the name of the account
     */
    String getName();

    /**
     * @return the description of the account
     */
    String getDescription();

    /**
     * @return the opening balance of the account
     */
    double getOpeningBalance();

    /**
     * @return the current balance of the account
     */
    double getBalance();

    /**
     * Add a new movement to the account.
     * It must add the transaction of the Movement to the Registry.
     *
     * @see it.unicam.cs.pa.jbudget097845.core.Registry
     * @see Movement
     *
     * @param m the movement to add
     * @throws AccountBalanceError if the account should not go under zero and the debit provided is too high
     */
    void addMovement(Movement m) throws AccountBalanceError;

    /**
     * @return the type of the account
     * @see it.unicam.cs.pa.jbudget097845.core.account.AccountType
     */
    AccountType getType();

    /**
     * @return the movements associated to the account
     */
    List<Movement> getMovements();

    /**
     * @param predicate predicate to filter movements
     * @return the filtered movements associated to the account
     */
    List<Movement> getMovements(Predicate<Movement> predicate);

    /**
     * Delete specific movements from the account.
     *
     * @param predicate predicate to filter the account movements
     */
    void deleteMovements(Predicate<Movement> predicate);

    /**
     * @param m the movement to delete
     */
    void deleteMovement(Movement m);
}
