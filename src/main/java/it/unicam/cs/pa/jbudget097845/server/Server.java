package it.unicam.cs.pa.jbudget097845.server;

import org.apache.log4j.BasicConfigurator;
import static spark.Spark.*;

import static spark.Spark.port;

/**
 * This Singleton class manage the Server.
 * It contains all the Endpoints available.
 *
 * @see Endpoints
 *
 */
public class Server {

    /**
     * Run the server.
     *
     * @param address the address of the server
     * @param port the port of the server
     */
    public static void run(String address, int port) {
        ipAddress(address);
        port(port);
        BasicConfigurator.configure();
        openEndpoints();
    }

    /**
     * Endpoints available
     */
    public static void openEndpoints() {
        Endpoints.newAccount();
        Endpoints.getAccounts();
        Endpoints.addTransaction();
        Endpoints.addTag();
    }

}
