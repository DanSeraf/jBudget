package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.server.Server;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Server.run("0.0.0.0", 22233);
    }
}
