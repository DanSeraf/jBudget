package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.account.Account;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    public void generateAccount() {
        Controller.init();
        Controller.generateAccount("Bank","Bank Account", "assets", "10000");
        Account generatedAccount = Controller.getRegistry().getAccount(a -> a.getName().equals("Bank"));
        assertEquals("Bank", generatedAccount.getName());
        assertEquals(10000, generatedAccount.getBalance());
    }

    @Test
    void getAccounts() {
        Controller.init();
        Controller.generateAccount("Bank","Bank Account", "assets", "10000");
        Controller.generateAccount("Loan","Loan Account", "liabilities", "100000");
        Map<String, Map<String, String>> accounts = Controller.getAccounts();
        assertEquals(2, accounts.size());
    }
}