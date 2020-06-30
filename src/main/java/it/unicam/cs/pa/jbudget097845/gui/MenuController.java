package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.core.ApplicationController;
import it.unicam.cs.pa.jbudget097845.core.Ledger;
import it.unicam.cs.pa.jbudget097845.core.account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private ApplicationController controller = new ApplicationController(Ledger.instance());

    @FXML
    private Label totalBalance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double tmp_balance = controller.getAccounts().stream().mapToDouble(Account::getBalance).sum();
        totalBalance.setText(Double.toString(tmp_balance));
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
}
