package it.unicam.cs.pa.jbudget097845.model;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    @Test
    void generateAccount() {
        ApplicationController controller = new ApplicationController(Ledger.instance());
        controller.generateAccount("Bank","Bank Account", "assets", "10000");
        Account generatedAccount = r.getAccount(p -> Objects.equals(p.getName(), "Bank"));
        assertEquals("Bank", generatedAccount.getName());
        assertEquals(10000, generatedAccount.getBalance());
    }

    @Test
    void getAccounts() {
        ApplicationController controller = new ApplicationController(Ledger.instance());
        controller.generateAccount("Bank","Bank Account", "assets", "10000");
        controller.generateAccount("Loan","Loan Account", "liabilities", "100000");
        Map<String, Map<String, String>> accounts = controller.getAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    void generateTransaction() {

    }
}