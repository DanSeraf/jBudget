package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;

/**
 *
 * This class is responsible of determine (from the input) which account should be created
 *
 */
public class AccountFactory {

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, Registry registry, boolean belowZero) throws AccountCreationError {
        switch (accountType) {
            case ASSETS: return new AssetAccount(openingBalance, name, description, accountType, belowZero, registry);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, Registry registry) throws AccountCreationError {
        switch (accountType) {
            case LIABILITIES: return new LiabilitiesAccount(openingBalance, name, description, accountType, registry);
            case ASSETS: return new AssetAccount(openingBalance, name, description, accountType, false, registry);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }
}
