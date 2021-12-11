package com.willhero.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //ImageView
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("WillHero");
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            new HelloController().exitFunc(stage);
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}