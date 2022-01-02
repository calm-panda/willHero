package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PauseScreen implements Initializable {
    @FXML private Button restart, saveGame, homeScreen;
    @FXML private AnchorPane scenePane;
    private Serialise serialise;
    @FXML
    protected void restartLvl(ActionEvent e) {
        // code
    }
    @FXML
    protected void saveLvl(ActionEvent e) {
        // serializable code
    }
    @FXML
    protected void homeScreenHandler(ActionEvent e) throws IOException {
        // code
    }
    private void Savegame() throws IOException {
        serialise.AddObj(Home.player, Home.player.getName());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage stage = (Stage) scenePane.getScene().getWindow();
//        stage.setResizable(false);
    }
}
