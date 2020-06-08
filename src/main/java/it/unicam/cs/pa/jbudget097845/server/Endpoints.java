package it.unicam.cs.pa.jbudget097845.server;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.pa.jbudget097845.ApplicationState;
import it.unicam.cs.pa.jbudget097845.core.Controller;
import it.unicam.cs.pa.jbudget097845.exc.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.exc.AccountTypeException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Endpoints {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void newAccount() {
        post("/newaccount", ((request, response) -> {
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String type = request.queryParams("type");
            String openingBalance = request.queryParams("opening_balance");

            try {
                Controller.generateAccount(name, description, type, openingBalance);
                response.status(200);
            } catch (AccountCreationError | AccountTypeException | NumberFormatException e){
                response.status(400);
            }
            return response;
        }));
    }

    public static void getAccounts() {
        get("/accounts", ((request, response) -> {
            Map<String, Map<String, String>> accounts;
            try {
                accounts = Controller.getAccounts();
            } catch (AccountNotFound anf) {
                return "NOACCOUNTS";
            }
            return mapper.writeValueAsString(accounts);
        }));
    }

    public static void addTransaction() {
        post("/addtransaction", ((request, response) -> {
            String movements_array = request.body();
            Controller.generateTransaction(new JSONArray(movements_array));
            return response;
        }));
    }

    public static void addTag() {
        post("/addtag", ((request, response) -> {
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Controller.addTag(name, description);
            return response;
        }));
    }
}
