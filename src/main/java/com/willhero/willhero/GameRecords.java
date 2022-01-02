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
import java.util.concurrent.ThreadLocalRandom;

public class GameRecords {
    @FXML private Button OK;
    @FXML private Button OK2;
    @FXML private TextField NameField;
    @FXML private TextField SearchField;
    //private Player player;
    @FXML private Label Reply;
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
        //HomeController.ComStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("gamescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        //Stage GameRecords.CommonStage = new Stage();//(scene);
//        HomeController.ComStage.setResizable(false);
//        HomeController.ComStage.setScene(scene);

        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
        AddUser();
    }
    @FXML//Ankit.txt
    private void Searching() throws InterruptedException {
        String name = SearchField.getText();
        System.out.println(Home.filesArr);
        name = name + ".txt";
        if(Home.filesArr.contains(name)){
            OK2.setOnAction(event -> {
                try {
                    Reply.setText("found a game");
                    OpenGameScreenFromSavedGames();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            Reply.setText("User Doesn't Exist!!..Add a new User");
            Thread.sleep(3000);
            OK2.setDisable(true);
            HomeController.ComStage.close();
        }
    }
    public static void OpenGameScreenFromSavedGames() throws IOException {
        GameRecords.CommonStage.close();
        HomeController.ComStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("gamescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
    }
}