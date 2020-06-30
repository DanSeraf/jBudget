package it.unicam.cs.pa.jbudget097845.core.command;

import it.unicam.cs.pa.jbudget097845.core.command.actions.ReceiveAction;
import it.unicam.cs.pa.jbudget097845.core.command.actions.SendAction;
import org.json.JSONArray;

/**
 * This class is responsible of manager the messages coming from the client.
 *
 */
@Deprecated
public class CommandHandler {
    public void handle(SendAction action, JSONArray jsonArray) {
        action.execute(jsonArray);
    }

    public String handle(ReceiveAction action) {
        return action.execute();
    }
}
