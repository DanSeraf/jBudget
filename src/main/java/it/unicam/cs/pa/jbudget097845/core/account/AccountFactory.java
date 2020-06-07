package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is responsible of determine which account should be created
 */

public class AccountFactory {

    private static AtomicLong ids = new AtomicLong(0);

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, boolean belowZero) throws AccountCreationError {
        switch (accountType) {
            case ASSETS: return new GeneralAssetAccount(ids.incrementAndGet(), openingBalance, name, description, belowZero);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance) throws AccountCreationError {
        switch (accountType) {
            case LIABILITIES: return new GeneralLiabilitiesAccount(ids.incrementAndGet(), openingBalance, name, description);
            case ASSETS: return new GeneralAssetAccount(ids.incrementAndGet(), openingBalance, name, description, false);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }
}
