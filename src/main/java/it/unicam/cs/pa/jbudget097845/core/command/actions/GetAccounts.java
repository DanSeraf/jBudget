package it.unicam.cs.pa.jbudget097845.core.command.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.pa.jbudget097845.core.ApplicationController;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import it.unicam.cs.pa.jbudget097845.exc.ActionError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAccounts implements ReceiveAction {

    private final ApplicationController controller = ApplicationController.instance();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String execute() {
        List<Account> accounts = controller.getAccounts();
        List<Map<String, String>> parsed_accounts = new ArrayList<>();
        for (Account acc : accounts) {
            parsed_accounts.add(new HashMap<>() {{
                put("name", acc.getName());
                put("description", acc.getDescription());
                put("balance", Double.toString(acc.getBalance()));
                put("opening_balance", Double.toString(acc.getOpeningBalance()));
            }});
        }

        try {
            return mapper.writeValueAsString(parsed_accounts);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        throw new ActionError("Error retreiving all the accounts");
    }
}
