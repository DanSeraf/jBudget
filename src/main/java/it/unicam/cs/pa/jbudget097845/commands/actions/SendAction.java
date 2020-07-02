package it.unicam.cs.pa.jbudget097845.commands.actions;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import org.json.JSONArray;

/**
 * This interface is implemented by the classes that want to parse
 * a message for the Controller.
 *
 * @see ApplicationController
 *
 */
@FunctionalInterface
public interface SendAction {
    void execute(JSONArray jsonArray);
}
