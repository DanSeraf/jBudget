package it.unicam.cs.pa.jbudget097845.gui.controllers;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.exc.Transaction.TransactionError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import it.unicam.cs.pa.jbudget097845.gui.Utils;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import org.javatuples.Quintet;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewTransactionController implements Initializable {

    private final ApplicationController controller = new ApplicationController();
    private final ScreenController screenController = ScreenController.instance();
    private static int nrows = 0;

    @FXML
    GridPane grid;
    @FXML
    Label responseMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Generate a new row with the fields necessary to create a new movement
     */
    @FXML
    private void newMovementField(ActionEvent event) {
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");
        TextField description = new TextField();
        description.setPromptText("Description");

        ComboBox<MovementType> movementType = new ComboBox<>();
        movementType.setPromptText("Movement type");
        movementType.getItems().addAll(MovementType.values());

        ComboBox<Account> accountsBox = new ComboBox<>();
        accountsBox.setPromptText("Accounts");
        List<Account> accounts = controller.getAccounts();
        for (Account a: accounts) {
            accountsBox.getItems().add(a);
        }

        List<Tag> tags = controller.getTags();
        CheckComboBox<Tag> tagsComboBox = new CheckComboBox<>();
        tagsComboBox.getItems().addAll(tags);
        tagsComboBox.setTitle("Tags");
        tagsComboBox.setShowCheckedCount(true);

        grid.addRow(nrows, accountsBox);
        grid.addRow(nrows, amountField);
        grid.addRow(nrows, description);
        grid.addRow(nrows, tagsComboBox);
        grid.addRow(nrows, movementType);
        nrows += 1;
    }

    private void resetGridPane() {
        grid.getChildren().clear();
        nrows = 0;
    }

    @FXML
    private void createNewTransaction(ActionEvent event) {
        List<Quintet<Double, MovementType, Account, List<Tag>, String>> movements = new ArrayList<>();

        try {
            for (int row = 0; row < nrows; row++) {
                ComboBox accountBox = (ComboBox) Utils.getNodeFromIndex(row, 0, grid);
                Account acc = (Account) accountBox.getValue();

                TextField amountField = (TextField) Utils.getNodeFromIndex(row, 1, grid);
                double amount = Double.parseDouble(amountField.getText());

                TextField descriptionField = (TextField) Utils.getNodeFromIndex(row, 2, grid);
                String description = descriptionField.getText();

                CheckComboBox tagsComboBox = (CheckComboBox) Utils.getNodeFromIndex(row, 3, grid);
                List<Tag> tags = tagsComboBox.getCheckModel().getCheckedItems();

                ComboBox<MovementType> movementBox = (ComboBox) Utils.getNodeFromIndex(row, 4, grid);
                MovementType mov_type = movementBox.getValue();

                movements.add(new Quintet(amount, mov_type, acc, tags, description));
            }
            controller.newTransaction(movements);
            resetGridPane();
            this.responseMessage.setText("Transaction created successfully");
        } catch (NullPointerException | NumberFormatException e) {
            this.responseMessage.setText("Some fields are empty or with wrong value");
        } catch (TransactionError e) {
            this.responseMessage.setText(e.getMessage());
        } catch (AccountNotFound e) {
            this.responseMessage.setText("You must select an account");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        resetGridPane();
        screenController.activate("menu");
    }
}
