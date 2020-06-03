package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    @DisplayName("Asset account generation")
    public void createAssetAccount() {
        AccountFactory accountManager = new AccountFactory();
        Account account = accountManager.newAccount(
                AccountType.ASSETS, "Bank", "Bank Account", 10000);

        assertEquals(10000, account.getBalance());
        assertEquals(10000, account.getOpeningBalance());
        assertEquals("Bank", account.getName());
        assertEquals("Bank Account", account.getDescription());
    }

    @Test
    @DisplayName("Liabilities account generation")
    public void createLiabilitiesAccount() {
        AccountFactory accountManager = new AccountFactory();
        Account account = accountManager.newAccount(
                AccountType.LIABILITIES, "Loan", "Loan expenses account", 10000);

        assertEquals(10000, account.getBalance());
        assertEquals(10000, account.getOpeningBalance());
        assertEquals("Loan", account.getName());
        assertEquals("Loan expenses account", account.getDescription());
    }

    @Test
    @DisplayName("Account creation error while creating an account that can't go under the zero")
    public void createAccountError() {
        AccountFactory accountManager = new AccountFactory();
        assertThrows(AccountCreationError.class, () -> accountManager.newAccount(
                AccountType.LIABILITIES, "Loan", "Loan expenses Account", 10000, true));

    }
}