package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.account.Account;
import it.unicam.cs.pa.jbudget097845.model.account.AccountType;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController implements Initializable {

    private final ApplicationController controller = new ApplicationController(Ledger.instance());

    @FXML
    private Label creditBalance;
    @FXML
    private Label debitBalance;
    @FXML
    private TableView<Movement> tableView;
    @FXML
    private TableColumn<Movement, String> accountCol;
    @FXML
    private TableColumn<Movement, String> movTypeCol;
    @FXML
    private TableColumn<Movement, String> amountCol;
    @FXML
    private TableColumn<Movement, String> dateCol;
    @FXML
    private TableColumn<Movement, String> descCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double asset_balance = controller.getAccounts().stream().filter(a -> a.getType() == AccountType.ASSETS).mapToDouble(Account::getBalance).sum();
        double liabilities_balance = controller.getAccounts().stream().filter(a -> a.getType() == AccountType.LIABILITIES).mapToDouble(Account::getBalance).sum();
        creditBalance.setText(Double.toString(asset_balance));
        debitBalance.setText(Double.toString(liabilities_balance));

        List<Movement> lastMovements = getLastMovements();

        if (lastMovements.size() != 0)
            tableView.setItems(FXCollections.observableArrayList(lastMovements));
            fillTableColumns();
    }

    private void fillTableColumns() {
        accountCol.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getAccount().getName())
        );

        movTypeCol.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getType().toString())
        );

        amountCol.setCellValueFactory(
                cell -> new SimpleStringProperty(Double.toString(cell.getValue().getAmount()))
        );

        dateCol.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getDate().toString())
        );

        descCol.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getDescription())
        );

    }

    private List<Movement> getLastMovements() {
        List<Transaction> transactions = controller.getTransactions();
        List<Transaction> lastTransactions = transactions.subList(Math.max(transactions.size() - 20, 0), transactions.size());
        return lastTransactions.stream()
                .map(Transaction::getMovements)
                .flatMap(List::stream)
                .collect(Collectors.toList());
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
