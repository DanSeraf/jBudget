package it.unicam.cs.pa.jbudget097845.core.command.actions;

/**
 * This interface is implemented by the classes that want to parse a message from the
 * controller to the client.
 */
@FunctionalInterface
public interface ReceiveAction {
    public String execute();
}
