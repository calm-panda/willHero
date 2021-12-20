package com.willhero.willhero;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import javafx.scene.media.MediaView;

import java.util.Objects;
import java.util.ResourceBundle;

public class Gamescreen implements Initializable {
    /////////////
    @FXML private Button restart, saveGame, homeScreen;
//    @FXML private AnchorPane scenePane;
    /////////////////////
    @FXML private ImageView islands11, islands4_1, islands4_2, islands1, islands3, islands2, islands5;
    private MediaPlayer mediaPlayer;
    private MediaView media;
    @FXML
    private StackPane parentContainer;
    //private static final String Media_url = ""
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView cloud1,cloud2,cloud3,cloud4,cloud5,cloud6;
    @FXML
    private ImageView OrcBoss, RedOrc1, tnt, hero, orc1, orc4, pause;
    static HelloController homeCtrl = new HelloController();
    private SequentialTransition heroTrans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        // orcs
        int pause = 0;
        homeCtrl.jump(OrcBoss,850,-60,pause);
        homeCtrl.jump(RedOrc1,950,-90,pause);
        homeCtrl.jump(orc1,1000,-130,pause);
        homeCtrl.jump(orc4,900,-120,pause);
        // hero
        heroTrans = homeCtrl.jump(hero,850,-130,pause);
        // tnt
        homeCtrl.tntTrans(tnt);
        // clouds
        homeCtrl.genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});

    }

    @FXML
    private void pauseEvent(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PauseScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 550);
        Stage newStage = new Stage();
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(scenePane.getScene().getWindow());
        newStage.setTitle("Pause Menu");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void clickAnimationHandler(MouseEvent e) {
        ImageView[] transImg = new ImageView[] {islands1,islands2,islands3,islands5,islands4_1,islands4_2,islands11,OrcBoss, RedOrc1, tnt, orc1, orc4};
        for (ImageView img : transImg) {
            transAnimation(img, 800, -120,false);
        }
        heroTrans.pause();
        transAnimation(hero,200,80,true);
        heroTrans.play();
    }

    protected void transAnimation(ImageView img, int duration,int byX, boolean rev) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setByX(byX);
        translate.setAutoReverse(rev);
        translate.play();
    }


    private void play_audio(){
        AudioClip note = new AudioClip(Objects.requireNonNull(this.getClass().getResource("Udd_Gaye.mp3")).toString());
        note.setVolume(0.5);
        note.play();
    }
}
