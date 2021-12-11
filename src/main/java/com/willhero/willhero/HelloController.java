package com.willhero.willhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML private ImageView cloud1, cloud2, cloud3, cloud4, cloud5, cloud6;
    @FXML private ImageView Orc1, RedOrc1, RedOrc2;
    @FXML private ImageView hero;
    @FXML private ImageView tnt;
    @FXML private ImageView quit;
    @FXML private AnchorPane scenePane;
    @FXML private StackPane parentContainer;
    private AudioClip note;
    private void clouds(ImageView cloud, int toX, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(cloud);
        translate.setDuration((Duration.millis(10000)));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setToX(toX);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    protected void genCloud(int toX, int[] pause, ImageView[] clouds) {
        for (int i = 0; i < pause.length; i++) {
            clouds(clouds[i],toX,pause[i]);
        }
    }

    protected void jump(ImageView img, int transTime, int toY, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(transTime));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(toY);
        translate.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    protected void tntTrans(ImageView img) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(tnt);
        scale.setDuration(Duration.millis(650));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setByX(0.12);
        scale.setByY(0.12);
        scale.setAutoReverse(true);
        scale.play();
    }

    @FXML
    private void gameScreen(MouseEvent e) throws IOException {
        note.stop();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gamescreen.fxml")));
        Scene scene = scenePane.getScene();
        sceneSwitch(root,parentContainer,scenePane);
    }

    protected void sceneSwitch(Parent sceneDir,StackPane stackPane, AnchorPane scenePanePara) throws IOException {
        sceneDir.translateXProperty().set(scenePanePara.getWidth());
        stackPane.getChildren().add(sceneDir);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(sceneDir.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> stackPane.getChildren().remove(scenePanePara));
        timeline.play();
    }

    @FXML
    private void exitGame(Event e) {
        exitFunc((Stage) scenePane.getScene().getWindow());
    }

    protected void exitFunc(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to quit?");
        alert.setHeaderText("You're exiting the Game!");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //clouds
        play_audio();
        genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});
        //hero
        jump(hero,850,-130,0);
        //orcs
        int pause = 500;
        jump(Orc1,1000,-130,pause);
        jump(RedOrc1,950,-90,pause);
        jump(RedOrc2,850,-60,pause);
        //tnt
        tntTrans(tnt);
    }
    private void play_audio(){
        note = new AudioClip(Objects.requireNonNull(this.getClass().getResource("Arcade.mp3")).toString());
        note.setCycleCount(AudioClip.INDEFINITE);
        note.play();
    }
}