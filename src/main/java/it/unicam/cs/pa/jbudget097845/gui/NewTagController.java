package it.unicam.cs.pa.jbudget097845.gui;

import it.unicam.cs.pa.jbudget097845.ApplicationController;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.exc.tag.TagException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTagController implements Initializable {

    @FXML
    private TextField tagName;
    @FXML
    private TextField tagDescription;
    @FXML
    private Label responseMessage;

    private final ApplicationController controller = new ApplicationController(Ledger.instance());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onSubmit(ActionEvent event) {
        if (!validateFields()) {
            this.responseMessage.setText("Some fields are empty");
            return;
        }

        try {
            controller.addTag(tagName.getText(), tagDescription.getText());
            this.responseMessage.setText("Tag correctly created");
        } catch (TagException te) {
            this.responseMessage.setText(te.getMessage());
        } finally {
            clearFields();
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene newAccountScene = new Scene(viewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newAccountScene);
        window.show();
    }

    private boolean validateFields() {
        return !tagName.getText().trim().isEmpty() && !tagDescription.getText().trim().isEmpty();
    }

    private void clearFields() {
        tagName.clear();
        tagDescription.clear();
    }
}
