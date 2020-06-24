package it.unicam.cs.pa.jbudget097845.core.command.actions;

import it.unicam.cs.pa.jbudget097845.core.Registry;
import org.json.JSONArray;

/**
 * This interface is implemented by the classes that want to parse
 * a message for the Controller.
 *
 * @see it.unicam.cs.pa.jbudget097845.core.ApplicationController
 *
 */
@FunctionalInterface
public interface SendAction {
    void execute(JSONArray jsonArray);
}
