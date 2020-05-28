package it.unicam.cs.pa.jbudget097845.core.account;

import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    @DisplayName("Account generation")
    public void createAccount() {
        AccountFactory accountManager = new AccountFactory();
        Account account = accountManager.newAccount(
                AccountType.ASSETS, "Bank", "Bank Account", 10000);

        assertEquals(10000, account.getBalance());
        assertEquals(10000, account.getOpeningBalance());
        assertEquals("Bank", account.getName());
        assertEquals("Bank Account", account.getDescription());
    }

    @Test
    @DisplayName("Account creation error while creating an account of type LIABILITIES")
    public void createAccountError() {
        AccountFactory accountManager = new AccountFactory();
        assertThrows(AccountCreationError.class, () -> accountManager.newAccount(
                AccountType.LIABILITIES, "Loan", "Loan expenses", 0, true));

    }
}