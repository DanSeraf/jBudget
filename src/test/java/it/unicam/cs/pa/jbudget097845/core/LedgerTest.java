package it.unicam.cs.pa.jbudget097845.core;

import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.exc.TransactionError;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerTest {

    @Test
    @DisplayName("Error on adding a new empty transaction to the registry")
    public void TestAddEmptyTransactionError() {
        Registry l = new Ledger();
        List<Tag> tags = new ArrayList<>();
        tags.add(new GeneralTag("Utilities", "Utilities expense"));
    }

}