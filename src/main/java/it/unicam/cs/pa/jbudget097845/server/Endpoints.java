package it.unicam.cs.pa.jbudget097845.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.commands.CommandHandler;
import it.unicam.cs.pa.jbudget097845.commands.actions.GenerateTag;
import it.unicam.cs.pa.jbudget097845.commands.actions.GenerateTransactions;
import it.unicam.cs.pa.jbudget097845.commands.actions.GetAccounts;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountTypeException;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import org.json.JSONArray;

//import static spark.Spark.get;
//import static spark.Spark.post;

/**
 * This class contains all the endpoints available for the Server in order to let the user
 * interact with the backend. The interaction with the backend is made by the ApplicationController.
 *
 * @see Server
 * @see ApplicationController
 *
 */
public class Endpoints {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ApplicationController controller = new ApplicationController();
    private static final CommandHandler commandHandler = new CommandHandler();

    /**
     * Manage the creation of a new account
     */
    public static void newAccount() {
        post("/newaccount", ((request, response) -> {
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String type = request.queryParams("type");
            String openingBalance = request.queryParams("opening_balance");

            try {
                //controller.generateAccount(name, description, type, openingBalance);
                response.status(200);
            } catch (AccountCreationError | AccountTypeException | NumberFormatException e){
                response.status(400);
            }
            return response;
        }));
    }

    /**
     * Get all the accounts available in the Registry
     *
     * @see it.unicam.cs.pa.jbudget097845.model.Registry
     */
    public static void getAccounts() {
        get("/accounts", ((request, response) -> {
            /**
            Map<String, Map<String, String>> accounts;
            try {
                accounts = controller.getAccounts();
            } catch (AccountNotFound anf) {
                return "NOACCOUNTS";
            }
             */
            return commandHandler.handle(new GetAccounts());
            //return mapper.writeValueAsString(accounts);
        }));
    }

    /**
     * Add a new transaction.
     * This will parse the request body in order to collect various movements.
     *
     * The body should contains the following parameters:
     * <p><ul>
     * <li>"account_name":"the name of the account"
     * <li>"movement_type":"credit/debit"
     * <li>"amount":"movement amount"
     * <li>"tags": [list of tags]
     * </ul></p>
     *
     * Request Example: [{"parameters"}, {"parameters"}, ...]
     */
    public static void addTransaction() {
        post("/addtransaction", ((request, response) -> {
            commandHandler.handle(
                    new GenerateTransactions(), new JSONArray(request.body()));
            return response;
        }));
    }

    /**
     * Add a new Tag.
     *
     * @see Tag
     */
    public static void addTag() {
        post("/addtag", ((request, response) -> {
            commandHandler.handle(
                    new GenerateTag(), new JSONArray(request.body()));
            return response;
        }));
    }
}
