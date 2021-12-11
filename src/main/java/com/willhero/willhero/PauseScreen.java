package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PauseScreen implements Initializable {
    @FXML private Button restart, saveGame, homeScreen;
    @FXML private AnchorPane scenePane;

    @FXML
    protected void restartLvl(ActionEvent e) {
        // code
    }
    @FXML
    protected void saveLvl(ActionEvent e) {
        // serializable code
    }
    @FXML
    protected void homeScreenHandler(ActionEvent e) {
        Stage pauseStage = (Stage) homeScreen.getScene().getWindow();
        pauseStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage stage = (Stage) scenePane.getScene().getWindow();
//        stage.setResizable(false);
    }
}
