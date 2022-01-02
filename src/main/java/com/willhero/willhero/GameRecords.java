package com.willhero.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameRecords {
    @FXML private Button OK;
    @FXML private TextField NameField;
    //private Player player;
    private Serialise serialise;
    public static Stage CommonStage = new Stage();
    private void AddUser(){
        String Name = NameField.getText();

        Home.player.setPlayerName(Name);
        Home.player.setCoinsNum(0);
        Home.player.SetScore(0);
    }
    @FXML
    private void OpenGameScreen(MouseEvent e) throws IOException {
        GameRecords.CommonStage.close();
        HomeController.ComStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("gamescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        //Stage GameRecords.CommonStage = new Stage();//(scene);
        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
        AddUser();

    }
}
