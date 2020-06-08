package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    @Test
    void generateAccount() {
        Registry r = new Ledger();
        ApplicationController controller = ApplicationController.instance();
        controller.init(r);
        controller.generateAccount("Bank","Bank Account", "assets", "10000");
        Account generatedAccount = r.getAccount(p -> Objects.equals(p.getName(), "Bank"));
        assertEquals("Bank", generatedAccount.getName());
        assertEquals(10000, generatedAccount.getBalance());
    }

    @Test
    void getAccounts() {
        ApplicationController controller = ApplicationController.instance();
        controller.init();
        controller.generateAccount("Bank","Bank Account", "assets", "10000");
        controller.generateAccount("Loan","Loan Account", "liabilities", "100000");
        Map<String, Map<String, String>> accounts = controller.getAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    void generateTransaction() {

    }
}