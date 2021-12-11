package com.willhero.willhero;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;
import javafx.scene.media.MediaView;
import java.util.ResourceBundle;

public class Gamescreen implements Initializable {
    private MediaPlayer mediaPlayer;
    private MediaView media;
    //private static final String Media_url = ""
    @FXML
    private ImageView OrcBoss, RedOrc1, tnt, hero, orc1, orc4, orc5;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(OrcBoss);
        translate1.setDuration(Duration.millis(850));
        translate1.setCycleCount((TranslateTransition.INDEFINITE));
        translate1.setByY(-60);
        translate1.setAutoReverse(true);
        translate1.play();
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(RedOrc1);
        translate2.setDuration(Duration.millis(900));
        translate2.setCycleCount((TranslateTransition.INDEFINITE));
        translate2.setByY(-60);
        translate2.setAutoReverse(true);
        translate2.play();
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(hero);
        translate3.setDuration(Duration.millis(750));
        translate3.setCycleCount((TranslateTransition.INDEFINITE));
        translate3.setByY(-75);
        translate3.setAutoReverse(true);
        translate3.play();
        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(orc1);
        translate4.setDuration(Duration.millis(720));
        translate4.setCycleCount((TranslateTransition.INDEFINITE));
        translate4.setByY(-75);
        translate4.setAutoReverse(true);
        translate4.play();
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(orc5);
        translate5.setDuration(Duration.millis(680));
        translate5.setCycleCount((TranslateTransition.INDEFINITE));
        translate5.setByY(-75);
        translate5.setAutoReverse(true);
        translate5.play();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(tnt);
        scale.setDuration(Duration.millis(700));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.setAutoReverse(true);
        scale.play();
    }
    private void play_audio(){
        AudioClip note = new AudioClip(this.getClass().getResource("Udd_Gaye.mp3").toString());
        note.play();
    }
}
