package it.unicam.cs.pa.jbudget097845.model.account;

import it.unicam.cs.pa.jbudget097845.model.Registry;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;

/**
 *
 * This class is responsible of determine (from the input) which account should be created
 *
 */
public class AccountFactory {

    public static Account newAccount(
            AccountType accountType, String name, String description,
            double openingBalance, boolean belowZero) {
        switch (accountType) {
            case LIABILITIES:
                if (belowZero) throw new AccountCreationError("Account 'liabilities' doesnt permit debit");
                return new LiabilitiesAccount(openingBalance, name, description, accountType);
            case ASSETS: return new AssetAccount(openingBalance, name, description, accountType, belowZero);
            default: throw new AccountCreationError("Error while creating a new account");
        }
    }
}
