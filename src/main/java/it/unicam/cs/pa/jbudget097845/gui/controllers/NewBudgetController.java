package it.unicam.cs.pa.jbudget097845.gui.controllers;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import it.unicam.cs.pa.jbudget097845.gui.Utils;
import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.budget.BudgetHandler;
import it.unicam.cs.pa.jbudget097845.model.budget.BudgetManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.javatuples.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewBudgetController implements Initializable {

    private ScreenController screenController = ScreenController.instance();
    private ApplicationController controller = new ApplicationController();
    private static int nrows = 0;

    @FXML
    GridPane grid;
    @FXML
    Label responseMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    private void newBudgetField() {
        TextField amountField = new TextField();
        amountField.setPromptText("Amount expected");

        ComboBox<Tag> tagsBox = new ComboBox<>();
        tagsBox.setPromptText("Tag");
        List<Tag> tags = controller.getTags();
        tags.forEach(t -> tagsBox.getItems().add(t));

        grid.addRow(nrows, tagsBox);
        grid.addRow(nrows, amountField);

        nrows += 1;
    }

    @FXML
    private void createNewBudget() {
        List<Pair<Tag, Double>> budgetValues = new ArrayList<>();
        try {
            for (int row = 0; row < nrows; row++) {
                ComboBox accountBox = (ComboBox) Utils.getNodeFromIndex(row, 0, grid);
                Tag tag = (Tag) accountBox.getValue();

                TextField amountField = (TextField) Utils.getNodeFromIndex(row, 1, grid);
                double expectedAmount = Double.parseDouble(amountField.getText());

                budgetValues.add(new Pair<>(tag, expectedAmount));
            }
            controller.addBudget(budgetValues);
            this.responseMessage.setText("Budget successfully created");
            resetGridPane();
        } catch (NullPointerException | NumberFormatException e) {
            this.responseMessage.setText("Some fields are empty or with an unexpected value");
        }
    }

    private void resetGridPane() {
        grid.getChildren().clear();
        nrows = 0;
    }

    @FXML
    private void goBack(ActionEvent event) {
        resetGridPane();
        screenController.activate("menu");
    }
}
