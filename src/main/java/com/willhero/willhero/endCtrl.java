package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class endCtrl {
    @FXML
    private Label score;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private void newGame(ActionEvent e) {
        // code
    }

    @FXML
    protected void exit(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResource("matJao.png")).toString())));
        alert.setTitle("Exit");
        alert.setContentText("Do you want to quit?");
        alert.setHeaderText("Tussi Ja Rahe ho !");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage st = (Stage) scenePane.getScene().getWindow();
            st.close();
        }
    }
}
