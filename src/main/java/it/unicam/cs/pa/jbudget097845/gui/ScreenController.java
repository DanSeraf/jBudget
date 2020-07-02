package it.unicam.cs.pa.jbudget097845.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ScreenController {
    private static ScreenController instance;
    private Map<String, Scene> screens = new HashMap<>();
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

    public void addScreen(String name, Parent pane) {
        screens.put(name, new Scene(pane));
    }

    public void activate(String name){
        primaryStage.setScene(screens.get(name));
        primaryStage.show();
    }
}
