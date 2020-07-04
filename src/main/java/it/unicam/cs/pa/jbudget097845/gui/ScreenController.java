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
    private Map<String, String> screens = new HashMap<>();
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
    }

    public void addScreen(String name, String fxml) {
        screens.put(name, fxml);
    }

    public void activate(ActionEvent event, String name) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(screens.get(name)))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void activate(String name) {
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(screens.get(name)))));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
