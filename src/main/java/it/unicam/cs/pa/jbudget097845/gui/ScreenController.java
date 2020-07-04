package it.unicam.cs.pa.jbudget097845.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScreenController {
    private static ScreenController instance;
    private static Map<String, String> screens = new HashMap<>();
    private static Stage primaryStage;

    private ScreenController() {}

    public static ScreenController instance() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

    public static void init(Stage rootStage) {
        primaryStage = rootStage;
        addScreen("menu", "menu.fxml");
        addScreen("new_account", "newaccount.fxml");
        addScreen("new_transaction", "newtransaction.fxml");
        addScreen("new_tag", "newtag.fxml");
        addScreen("new_budget", "newbudget.fxml");
        addScreen("movements_view", "movementsoverview.fxml");
        addScreen("expenses_overview", "expensesoverview.fxml");
    }

    public static void addScreen(String name, String fxml) {
        screens.put(name, fxml);
    }

    public void activate(String name) {
        try {
            if (name.equals("menu"))
                primaryStage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource(screens.get(name)))));
            else
                primaryStage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("./controllers/" + screens.get(name)))));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
