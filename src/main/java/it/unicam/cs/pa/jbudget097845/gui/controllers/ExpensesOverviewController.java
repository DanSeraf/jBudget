package it.unicam.cs.pa.jbudget097845.gui.controllers;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;
import it.unicam.cs.pa.jbudget097845.model.movement.Movement;
import it.unicam.cs.pa.jbudget097845.model.movement.MovementType;
import it.unicam.cs.pa.jbudget097845.model.transaction.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class ExpensesOverviewController implements Initializable {

    private ApplicationController controller = new ApplicationController();
    private ScreenController screenController = ScreenController.instance();

    @FXML
    private PieChart pieChart;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Label errorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void generateChart() {
        resetChart();
        if (fromDate.getValue().isAfter(toDate.getValue())) {
            this.errorMessage.setText("Invalid date range");
            return;
        }

        List<Transaction> transactions = controller.getTransactions();
        List<Tag> tags = controller.getTags();
        Map<Tag, Double> mappedTags = new HashMap<>();

        tags.forEach(tag -> {
            Double amount = transactions.stream()
                    .map(Transaction::getMovements)
                    .flatMap(Collection::stream)
                    .filter(m -> m.getTags().contains(tag))
                    .filter(m -> m.getDate().isBefore(toDate.getValue()) || m.getDate().isEqual(toDate.getValue()))
                    .filter(m -> m.getDate().isAfter(fromDate.getValue()) || m.getDate().isEqual(fromDate.getValue()))
                    .filter(m -> m.getType() == MovementType.DEBIT)
                    .map(Movement::getAmount)
                    .reduce(0.0, Double::sum);
            if (amount > 0) {
                mappedTags.put(tag, amount);
            }
        });

        mappedTags.forEach((t, am) -> {
            System.out.println(t.getName());
            System.out.println(am);
        });

        if (mappedTags.isEmpty()) {
            errorMessage.setText("No movements found for the input dates");
            return;
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        mappedTags.forEach((t,a) -> {
            pieChartData.add(new PieChart.Data(t.getName() + " " + a.toString(), a));
        });
        pieChart.setTitle("Expenses by tag");
        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
    }

    @FXML
    private void goBack() {
        screenController.activate("menu");
    }

    private void resetChart() {
        pieChart.getData().removeAll();
    }
}
