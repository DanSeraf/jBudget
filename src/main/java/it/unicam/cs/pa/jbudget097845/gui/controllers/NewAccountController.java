package it.unicam.cs.pa.jbudget097845.gui.controllers;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountController implements Initializable {

    private final ApplicationController controller = new ApplicationController(Ledger.instance());
    private final ScreenController screenController = ScreenController.instance();

    @FXML
    private ChoiceBox<AccountType> accountTypes = new ChoiceBox<>();
    @FXML
    private TextField accountName;
    @FXML
    private TextField accountOpeningBalance;
    @FXML
    private TextField accountDescription;
    @FXML
    private Label responseMessage;
    @FXML
    private CheckBox belowZero;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accountTypes.getItems().addAll(AccountType.values());
    }

    @FXML
    private void createNewAccount(ActionEvent event) {
        if (!validateFields()) {
            this.responseMessage.setText("Some fields are empty");
            return;
        }

        try {
            String account_name = accountName.getText();
            String account_description = accountDescription.getText();
            AccountType account_type = accountTypes.getValue();
            double opening_balance = Double.parseDouble(accountOpeningBalance.getText());
            controller.generateAccount(
                    account_name, account_description, account_type, opening_balance, belowZero.isSelected());
            this.responseMessage.setText("Account correctly created");
            clearFields();
        } catch (AccountCreationError ace) {
            this.responseMessage.setText(ace.getMessage());
        } catch (NumberFormatException nfe) {
            this.responseMessage.setText("Wrong balance value");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        screenController.activate(event, "menu");
    }

    private boolean validateFields() {
        return accountName.getText() != null
                && accountDescription.getText() != null
                && accountOpeningBalance.getText() != null
                && accountTypes.getValue() != null;
    }

    private void clearFields() {
        accountName.clear();
        accountOpeningBalance.clear();
        accountDescription.clear();
    }

}
