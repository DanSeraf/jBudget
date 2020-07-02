package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.exc.Transaction.TransactionError;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountNotFound;
import it.unicam.cs.pa.jbudget097845.model.Tag;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewTransactionController implements Initializable {

    @FXML
    GridPane grid;
    @FXML
    Label responseMessage;

    private ApplicationController controller = new ApplicationController();
    private static int nrow = 0;

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

        grid.addRow(nrow, accountsBox);
        grid.addRow(nrow, amountField);
        grid.addRow(nrow, description);
        grid.addRow(nrow, tagsComboBox);
        grid.addRow(nrow, movementType);
        nrow += 1;
    }

    private Node getNodeFromIndex(int row, int col) {
        for (Node n : grid.getChildren()) {
            if (GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == col) {
                return n;
            }
        }
        return null;
    }

    private void resetGridPane() {
        grid.getChildren().clear();
        nrow = 0;
    }

    @FXML
    private void createNewTransaction(ActionEvent event) {
        List<Quintet<Double, MovementType, Account, List<Tag>, String>> movements = new ArrayList<>();

        int nRow = grid.getRowCount();
        try {
            for (int row = 0; row < nRow; row++) {
                ComboBox accountBox = (ComboBox) getNodeFromIndex(row, 0);
                Account acc = (Account) accountBox.getValue();

                TextField amountField = (TextField) getNodeFromIndex(row, 1);
                double amount = Double.parseDouble(amountField.getText());

                TextField descriptionField = (TextField) getNodeFromIndex(row, 2);
                String description = descriptionField.getText();

                CheckComboBox tagsComboBox = (CheckComboBox) getNodeFromIndex(row, 3);
                List<Tag> tags = tagsComboBox.getCheckModel().getCheckedItems();

                ComboBox<MovementType> movementBox = (ComboBox) getNodeFromIndex(row, 4);
                MovementType mov_type = movementBox.getValue();

                movements.add(new Quintet(amount, mov_type, acc, tags, description));
            }
            controller.newTransaction(movements);
            resetGridPane();
            this.responseMessage.setText("Transaction created successfully");
        } catch (NullPointerException | NumberFormatException e) {
            this.responseMessage.setText("Some fields are empty");
        } catch (TransactionError e) {
            this.responseMessage.setText(e.getMessage());
        } catch (AccountNotFound e) {
            this.responseMessage.setText("You must select an account");
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        resetGridPane();
        Parent viewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene newAccountScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
    }
}
