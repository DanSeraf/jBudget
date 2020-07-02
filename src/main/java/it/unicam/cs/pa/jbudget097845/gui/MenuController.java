package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private ApplicationController controller = new ApplicationController(Ledger.instance());

    @FXML
    private Label creditBalance;
    @FXML
    private Label debitBalance;
    @FXML
    private TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double asset_balance = controller.getAccounts().stream().filter(a -> a.getType() == AccountType.ASSETS).mapToDouble(Account::getBalance).sum();
        double liabilities_balance = controller.getAccounts().stream().filter(a -> a.getType() == AccountType.LIABILITIES).mapToDouble(Account::getBalance).sum();
        creditBalance.setText(Double.toString(asset_balance));
        debitBalance.setText(Double.toString(liabilities_balance));


    }

    @FXML
    private void onExit() {
        System.exit(0);
    }

    @FXML
    private void loadNewAccountView(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("newaccount.fxml"));
        Scene newAccountScene = new Scene(viewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
    }

    @FXML
    private void LoadNewTagView(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("newtag.fxml"));
        Scene newAccountScene = new Scene(viewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
    }

    @FXML
    private void LoadNewTransactionView(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("newtransaction.fxml"));
        Scene newAccountScene = new Scene(viewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
    }
}
