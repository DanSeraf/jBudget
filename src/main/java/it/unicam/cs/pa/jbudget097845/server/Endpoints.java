package it.unicam.cs.pa.jbudget097845.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.pa.jbudget097845.core.Controller;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Endpoints {

    public void newAccount() {
        post("/newaccount", ((request, response) -> {
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String type = request.queryParams("type");
            String openingBalance = request.queryParams("opening_balance");

            try {
                Controller.generateAccount(name, description, type, openingBalance);
                response.status(200);
            } catch (AccountCreationError ace){
                response.status(500);
            } finally {
                if (response.status() == 200) return "SUCCESS";
                else return "FAILED";
            }
        }));
    }

    public void getAccounts() {
        get("/accounts", ((request, response) -> {
            Map<String, Map<String, String>> accounts = Controller.getAccounts();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(accounts);
        }));
    }
}
