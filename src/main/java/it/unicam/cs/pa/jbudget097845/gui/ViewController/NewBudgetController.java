package it.unicam.cs.pa.jbudget097845.gui.ViewController;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.gui.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class NewBudgetController implements Initializable {

    private ScreenController screenController = ScreenController.instance();
    private ApplicationController controller = new ApplicationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void goBack(ActionEvent event) {
        screenController.activate("menu");
    }
}
