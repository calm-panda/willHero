package com.willhero.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Home extends Application {
    public static File folder = new File("C:\\Users\\jmd\\Desktop\\willHero\\src\\main\\resources\\com\\willhero\\willhero\\Saved Games");
    public static File[] fileslist = folder.listFiles();
    public static ArrayList<String> filesArr = new ArrayList<>();

    public static Serialise serialise;
    static {
        serialise = new Serialise();
    }
    public static Player player = new Player();
    @Override
    public void start(Stage stage) throws IOException {
        GameRecords.CommonStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        GameRecords.CommonStage.setTitle("WillHero");
        GameRecords.CommonStage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            new HomeController().exitFunc(GameRecords.CommonStage);
        });
        GameRecords.CommonStage.setResizable(false);
        GameRecords.CommonStage.setScene(scene);
        GameRecords.CommonStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}