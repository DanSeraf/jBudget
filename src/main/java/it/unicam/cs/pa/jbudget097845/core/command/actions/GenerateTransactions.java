package it.unicam.cs.pa.jbudget097845.core.command.actions;

import it.unicam.cs.pa.jbudget097845.core.ApplicationController;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;
import it.unicam.cs.pa.jbudget097845.core.transaction.TransactionManager;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateTransactions implements SendAction {

    private final TransactionManager transactionManager = TransactionManager.instance();
    private final ApplicationController controller = ApplicationController.instance();

    @Override
    public void execute(JSONArray jsonArray) {
        Transaction t = transactionManager.newTransaction(LocalDate.now());

        for (int i = 0; i < jsonArray.length(); i++) {
            String account_name = jsonArray.getJSONObject(i).getString("account_name");
            String movement_type = jsonArray.getJSONObject(i).getString("movement_type");
            String amount = jsonArray.getJSONObject(i).getString("amount");

            JSONArray tags_array = jsonArray.getJSONObject(i).getJSONArray("tags");
            List<String> tagNames = new ArrayList<>();
            for (int j = 0; j < tags_array.length(); j++) {
                String tag_name = tags_array.getString(i);
                tagNames.add(tag_name);
            }

            controller.newTransaction(account_name, t, movement_type, amount, tagNames);
        }

    }
}