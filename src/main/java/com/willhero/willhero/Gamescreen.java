package com.willhero.willhero;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gamescreen implements Initializable {
    /////////////
    @FXML private Button restart, saveGame, homeScreen;
//    @FXML private AnchorPane scenePane;
    /////////////////////
    @FXML private ImageView tree1, tree4, chestC;
    @FXML private ImageView islands11, islands4_1, islands4_2, islands1, islands5;
    private MediaPlayer mediaPlayer;
    private MediaView media;
    @FXML
    private StackPane parentContainer;
    private final HashMap<String,Image[]> assets = loadAssets();
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView cloud1,cloud2,cloud3,cloud4,cloud5,cloud6;
    @FXML
    private ImageView tnt, hero, orc1, orc4, pause;
    static HomeController homeCtrl = new HomeController();
//    private SequentialTransition heroTrans;  // stop hero in air
    private ArrayList<ImageView> allTrans = new ArrayList<>();
    TranslateTransition heroTran;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        // orcs
//        int pause = 0;
//        scenePane.getChildren().add(assets.get("Trees")[1]);
//        jump();(OrcBoss,850,-60,pause);
//        jump();(RedOrc1,950,-90,pause);
        jump(orc1,1000,-130,true);
        jump(orc4,900,-120,true);
        // hero
        heroTran = jump(hero,850,-130,true);
        // tnt
        homeCtrl.tntTrans(tnt);
        // clouds
        homeCtrl.genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});
        allTrans.addAll(List.of(islands1, islands5, islands4_1, islands4_2, islands11, tnt, orc1, orc4, tree1,tree4,chestC));
        // adding imageView from controller
        ImageView img1 = loadIsland(1350,477,false);
        allTrans.add(img1);
        scenePane.getChildren().add(img1);
        int prev = (int) (1350 + (img1.getFitWidth()/2) + ThreadLocalRandom.current().nextInt(15,100));
        //////////////////////////////////// number of islands
        for (int i = 0; i < 100; i++) {
            ImageView img = loadIsland(prev,477,true);
            prev += (int)(img.getFitWidth() / 2) + ThreadLocalRandom.current().nextInt(100,160);
            scenePane.getChildren().add(img);
            allTrans.add(img);
        }

    }

    private ImageView loadIsland(int x, int y, boolean addPreDis) {
        int islandNum = ThreadLocalRandom.current().nextInt(0, assets.get("Islands").length);
        ImageView img = new ImageView(assets.get("Islands")[islandNum]);
        img.setFitHeight(ThreadLocalRandom.current().nextInt(50, 120));
        img.setFitWidth(ThreadLocalRandom.current().nextInt(100, 220));
        if (addPreDis) {
            img.setX(x+(img.getFitWidth()/2));
        } else {
            img.setX(x);
        }
        img.setY(y);
        transAnimation(img,800,1,-120,false,0);
        return img;
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
        heroTran.pause();
        psuedoForward(hero,60,1,80,false,0);
        for (ImageView img : allTrans) {
            transAnimation(img, 200,1,-120,false,100);
        }// 283
        psuedoForward(hero, 200,1,0,false,160);
        heroTran.setDuration(Duration.millis(700));
        heroTran.play();
    }

    private HashMap<String,Image[]> loadAssets() {
        HashMap<String,Image[]> assets = new HashMap<>();
        assets.put("Rocks",new Image[4]);
        assets.put("Coin",new Image[] {new Image(String.valueOf(getClass().getResource("assets/Coin.png")))});
        assets.put("Islands",new Image[11]);
        assets.put("Orcs",new Image[6]);
//        assets.put("RedOrcs",new ImageView[3]);
        assets.put("Trees",new Image[4]);
//        assets.put("")    for weapons
        for (int i = 0; i < 4; i++) {
            assets.get("Rocks")[i] = new Image(String.valueOf(getClass().getResource("assets/BalancingRocks"+(i+2)+".png")));
        }
        for (int i = 0; i < 11; i++) {
            assets.get("Islands")[i] = new Image(String.valueOf(getClass().getResource("assets/Islands"+(i+1)+".png")));
        }
        for (int i = 0; i < 5; i++) {
            assets.get("Orcs")[i] = new Image(String.valueOf(getClass().getResource("assets/Orc"+(i+1)+".png")));
        }
        assets.get("Orcs")[5] = new Image(String.valueOf(getClass().getResource("assets/OrcBoss.png")));
//        for (int i = 0; i < 5; i++) {  // for red orcs
//            assets.get("Orcs")[i] = new Image("assets/Orc"+(i+1)+".png");
//        }
        for (int i = 0; i < 4; i++) {
            assets.get("Trees")[i] = new Image(String.valueOf(getClass().getResource("assets/Tree"+(i+1)+".png")));
        }
        return assets;
    }

    private void transAnimation(ImageView img, int duration, int times, int byX, boolean rev, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
        translate.setByX(byX);
        translate.setAutoReverse(rev);
        translate.setInterpolator(Interpolator.EASE_IN);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    private void psuedoForward(ImageView img, int duration, int times, int toX, boolean rev, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
        translate.setToX(toX);
        translate.setAutoReverse(rev);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    private TranslateTransition jump(ImageView img, int duration, int byY, boolean isRev) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(byY);
        translate.setAutoReverse(isRev);
//        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
//        seqTransition.play();
        translate.play();
        return translate;
    }

    private void play_audio(){
        AudioClip note = new AudioClip(Objects.requireNonNull(this.getClass().getResource("Udd_Gaye.mp3")).toString());
        note.setVolume(0.2);
        note.play();
    }
}
