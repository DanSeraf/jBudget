package it.unicam.cs.pa.jbudget097845.core.account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.unicam.cs.pa.jbudget097845.core.GeneralTag;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;

import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Account.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeneralAssetAccount.class, name = "general_asset_account"),
        @JsonSubTypes.Type(value = GeneralLiabilitiesAccount.class, name = "general_liabilities_account")
})
public interface Account {
    String getName();

    String getDescription();

    double getOpeningBalance();

    double getBalance();

    void addMovement(Movement m);

    AccountType getType();

    List<Movement> getMovements();

    List<Movement> getMovements(Predicate<Movement> predicate);
}
