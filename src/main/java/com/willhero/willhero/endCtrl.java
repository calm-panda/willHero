package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class endCtrl implements Initializable {
    @FXML
    private Label score;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private void newGame(ActionEvent e) throws IOException {
        GameRecords.CommonStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("gamescreen.fxml"));
        Gamescreen.user.setCoinsNum(0);
        Gamescreen.user.setScore(0);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
    }

    @FXML
    private void mainMenu(ActionEvent e) throws IOException {
        GameRecords.CommonStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score.setText(String.valueOf(Gamescreen.user.getScore()));
    }
}
