package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.server.Server;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        ipAddress("0.0.0.0");
        port(4559);
        BasicConfigurator.configure();
        Server.run();
    }
}
