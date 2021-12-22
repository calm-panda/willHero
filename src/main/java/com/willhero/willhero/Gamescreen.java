package com.willhero.willhero;

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
    private SequentialTransition heroTrans;
    private ArrayList<ImageView> allTrans = new ArrayList<>();
    private int tapCounter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        // orcs
        int pause = 0;
//        scenePane.getChildren().add(assets.get("Trees")[1]);
//        homeCtrl.jump(OrcBoss,850,-60,pause);
//        homeCtrl.jump(RedOrc1,950,-90,pause);
        homeCtrl.jump(orc1,1000,-130,pause);
        homeCtrl.jump(orc4,900,-120,pause);
        // hero
        heroTrans = homeCtrl.jump(hero,850,-130,pause);
        // tnt
        homeCtrl.tntTrans(tnt);
        // clouds
        homeCtrl.genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});
        allTrans.addAll(List.of(islands1, islands5, islands4_1, islands4_2, islands11, tnt, orc1, orc4, tree1,tree4,chestC));
        // adding imageView from controller
        ImageView img1 = loadIsland(1350,477);
        allTrans.add(img1);
//        ImageView img2 = loadIsland(500,477);
//        ImageView img3 = loadIsland(750,477);
        scenePane.getChildren().addAll(img1);

    }

    private ImageView loadIsland(int x, int y) {
        int islandNum = ThreadLocalRandom.current().nextInt(0, assets.get("Islands").length);
        ImageView img = new ImageView(assets.get("Islands")[islandNum]);
        img.setFitHeight(ThreadLocalRandom.current().nextInt(50, 120));
        img.setFitWidth(ThreadLocalRandom.current().nextInt(80, 220));
        img.setX(x);
        img.setY(y);
        transAnimation(img,800,1,-120,false);
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
        tapCounter++;
        heroTrans.pause();
        transAnimation(hero,200,2,120,true);
        heroTrans.play();
        if (tapCounter >= 2) {
            tapCounter = 0;
            ImageView img = loadIsland(1370,477);
            scenePane.getChildren().add(img);
            allTrans.add(img);
        }
        for (ImageView img : allTrans) {
            transAnimation(img, 600,1,-150,false);
        }

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

    private void transAnimation(ImageView img, int duration, int times, int byX, boolean rev) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
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
