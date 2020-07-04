package it.unicam.cs.pa.jbudget097845.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {

    private ScreenController screenController = ScreenController.instance();

    @Override
    public void start(Stage primaryStage) {
        initScenes(primaryStage);
        primaryStage.setTitle("jBudget");
        screenController.activate("menu");
    }

    private void initScenes(Stage primaryStage) {
        ScreenController.init(primaryStage);
    }
}
