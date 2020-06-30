package it.unicam.cs.pa.jbudget097845.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("jBudget");

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
