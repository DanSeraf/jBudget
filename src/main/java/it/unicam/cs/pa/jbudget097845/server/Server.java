package it.unicam.cs.pa.jbudget097845.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.pa.jbudget097845.core.Controller;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import org.apache.log4j.BasicConfigurator;
import static spark.Spark.*;

import java.io.StringWriter;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.port;

public class Server {

    private Server class_instance = null;

    private Server () {
    }

    private Server Server() {
        if (this.class_instance == null) {
            this.class_instance = new Server();
        }
        return this.class_instance;
    }

    public static void run(String address, int port) {
        ipAddress(address);
        port(port);
        BasicConfigurator.configure();
        openEndpoints();
    }

    public static void openEndpoints() {
        Endpoints.newAccount();
        Endpoints.getAccounts();
        Endpoints.addTransaction();
        Endpoints.addTag();
    }

}
