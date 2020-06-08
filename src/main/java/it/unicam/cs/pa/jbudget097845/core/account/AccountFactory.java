package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is responsible of determine which account should be created
 */

public class AccountFactory {

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, Registry registry, boolean belowZero) throws AccountCreationError {
        switch (accountType) {
            case ASSETS: return new GeneralAssetAccount(openingBalance, name, description, accountType, belowZero, registry);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, Registry registry) throws AccountCreationError {
        switch (accountType) {
            case LIABILITIES: return new GeneralLiabilitiesAccount(openingBalance, name, description, accountType, registry);
            case ASSETS: return new GeneralAssetAccount(openingBalance, name, description, accountType, false, registry);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }
}
