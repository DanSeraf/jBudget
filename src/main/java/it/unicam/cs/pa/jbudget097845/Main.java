package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.core.Ledger;
import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.core.movement.Movement;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementManager;
import it.unicam.cs.pa.jbudget097845.core.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import it.unicam.cs.pa.jbudget097845.server.Server;
import org.apache.log4j.BasicConfigurator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        ipAddress("0.0.0.0");
        port(4559);
        BasicConfigurator.configure();
        server.openEndpoint();
    }
}
