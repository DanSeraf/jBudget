package it.unicam.cs.pa.jbudget097845.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {

    private ScreenController screenController = ScreenController.instance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        initScenes(primaryStage);
        primaryStage.setTitle("jBudget");
        screenController.activate("menu");
    }

    private void initScenes(Stage primaryStage) throws IOException {
        ScreenController.init(primaryStage);
        screenController.addScreen("menu", "menu.fxml");
        screenController.addScreen("new_account", "newaccount.fxml");
        screenController.addScreen("new_transaction", "newtransaction.fxml");
        screenController.addScreen("new_tag", "newtag.fxml");
        screenController.addScreen("new_budget", "newbudget.fxml");
        screenController.addScreen("movements_view", "movementsoverview.fxml");
    }
}
