package it.unicam.cs.pa.jbudget097845.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {

    private ScreenController screenController = ScreenController.instance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        initScreens(primaryStage);
        primaryStage.setTitle("jBudget");
        screenController.activate("menu");
    }

    private void initScreens(Stage primaryStage) throws IOException {
        ScreenController.init(primaryStage);
        screenController.addScreen("menu", FXMLLoader.load(getClass().getResource("menu.fxml")));
        screenController.addScreen("new_account", FXMLLoader.load(getClass().getResource("newaccount.fxml")));
        screenController.addScreen("new_transaction", FXMLLoader.load(getClass().getResource("newtransaction.fxml")));
        screenController.addScreen("new_tag", FXMLLoader.load(getClass().getResource("newtag.fxml")));
        screenController.addScreen("movements_view", FXMLLoader.load(getClass().getResource("movementsoverview.fxml")));
    }
}
