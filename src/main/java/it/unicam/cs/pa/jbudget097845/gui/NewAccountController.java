package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.core.ApplicationController;
import it.unicam.cs.pa.jbudget097845.core.Ledger;
import it.unicam.cs.pa.jbudget097845.core.account.AccountType;
import it.unicam.cs.pa.jbudget097845.exc.account.AccountCreationError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountController implements Initializable {
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

    private ApplicationController controller = new ApplicationController(Ledger.instance());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accountTypes.getItems().add(AccountType.ASSETS);
        this.accountTypes.getItems().add(AccountType.LIABILITIES);
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
            controller.generateAccount(account_name, account_description, account_type, opening_balance);
            this.responseMessage.setText("Account correctly created");
        } catch (AccountCreationError ace) {
            this.responseMessage.setText(ace.getMessage());
        } catch (NumberFormatException nfe) {
            this.responseMessage.setText("Wrong balance value");
        } finally {
            clearFields();
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene newAccountScene = new Scene(viewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
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
