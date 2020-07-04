package it.unicam.cs.pa.jbudget097845.gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Methods useful for the ViewController classes
 *
 */
public class Utils {
    public static Node getNodeFromIndex(int row, int col, GridPane grid) {
        for (Node n : grid.getChildren()) {
            if (GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == col) {
                return n;
            }
        }
        return null;
    }
}
