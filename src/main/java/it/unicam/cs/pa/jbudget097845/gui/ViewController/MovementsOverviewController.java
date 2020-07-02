package it.unicam.cs.pa.jbudget097845.gui.ViewController;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MovementsOverviewController implements Initializable {

    private final ApplicationController controller = new ApplicationController();
    private final ScreenController screenController = ScreenController.instance();

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
        List<Movement> movements = controller.getTransactions().stream()
                .map(Transaction::getMovements)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if (movements.size() != 0)
            tableView.setItems(FXCollections.observableArrayList(movements));
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

    @FXML
    private void goBack(ActionEvent event) {
        screenController.activate("menu");
    }
}
