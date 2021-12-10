package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseScreen {
    @FXML private Button restart, saveGame, homeScreen;

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
}
