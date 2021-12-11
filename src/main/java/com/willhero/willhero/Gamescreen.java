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

import java.util.Objects;
import java.util.ResourceBundle;

public class Gamescreen implements Initializable {
    private MediaPlayer mediaPlayer;
    private MediaView media;
    //private static final String Media_url = ""
    @FXML
    private ImageView cloud1,cloud2,cloud3,cloud4,cloud5,cloud6;
    @FXML
    private ImageView OrcBoss, RedOrc1, tnt, hero, orc1, orc4;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        HelloController homeCtrl = new HelloController();
        // orcs
        int pause = 0;
        homeCtrl.jump(OrcBoss,850,-60,pause);
        homeCtrl.jump(RedOrc1,950,-90,pause);
        homeCtrl.jump(orc1,1000,-130,pause);
        homeCtrl.jump(orc4,900,-120,pause);
        //hero
        homeCtrl.jump(hero,850,-130,pause);
        // tnt
        homeCtrl.tntTrans(tnt);
        // clouds
        homeCtrl.genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});

    }
    private void play_audio(){
        AudioClip note = new AudioClip(Objects.requireNonNull(this.getClass().getResource("Udd_Gaye.mp3")).toString());
        note.play();
    }
}
