package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is responsible of determine which account should be created
 */

public class AccountFactory {

    private static AtomicInteger ids = new AtomicInteger(0);

    public Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, boolean hasLimit) throws AccountCreationError {
        switch (accountType) {
            case ASSETS: return new GeneralLiabilitiesAccount(ids.incrementAndGet(), openingBalance, name, description, hasLimit);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }

    public Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance) throws AccountCreationError {
        switch (accountType) {
            case LIABILITIES: return new GeneralAssetAccount(ids.incrementAndGet(), openingBalance, name, description);
            case ASSETS: return new GeneralLiabilitiesAccount(ids.incrementAndGet(), openingBalance, name, description, true);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }
}
