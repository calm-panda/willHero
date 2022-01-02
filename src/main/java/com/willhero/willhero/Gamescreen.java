package com.willhero.willhero;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import javafx.scene.media.MediaView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gamescreen implements Initializable,animate {
    /////////////
    @FXML private Rectangle hero, heroCross;
//    @FXML private AnchorPane scenePane;
    /////////////////////
    @FXML private Label scoreLbl;
    @FXML private ImageView tree1, tree4, chest1;
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
    private ImageView tnt, orc1, orc4, pause;
    static HomeController homeCtrl = new HomeController();
    //    private SequentialTransition heroTrans;  // stop hero in air
    private HashMap<String, ArrayList<ImageView>> objects = new HashMap<>();
    TranslateTransition heroTran;

    AnimationTimer collisionTimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_audio();
        int zIdx = 5;
        // hero
        heroTran = jump(hero,850,-130,true);
        // tnt
        homeCtrl.tntTrans(tnt);
        // clouds
        homeCtrl.genCloud(-2200,new int[] {0,0,1000,1000,3000,3000}, new ImageView[] {cloud1,cloud2,cloud3,cloud4,cloud5,cloud6});
        objects.put("islands",new ArrayList<>());
        objects.put("trees",new ArrayList<>());
        objects.put("chests",new ArrayList<>());
        objects.put("tnts",new ArrayList<>());
        objects.put("orcs",new ArrayList<>());
        objects.put("openChests",new ArrayList<>());
        objects.get("islands").addAll(List.of(islands1, islands5, islands4_1, islands4_2, islands11));
        objects.get("trees").addAll(List.of(tree1,tree4));
        objects.get("chests").add(chest1);
        objects.get("tnts").add(tnt);
        objects.get("orcs").addAll(List.of(orc1, orc4));
        hero.setFill(new ImagePattern(new Image(String.valueOf(getClass().getResource("assets/Helmet4.png")))));
        // adding imageView from controller
        ImageView img1 = loadIsland(1350,477,false);
        objects.get("islands").add(img1);
        scenePane.getChildren().add(zIdx,img1);
        int prev = (int) (850 + (img1.getFitWidth()/2) + ThreadLocalRandom.current().nextInt(-4,90));
        //////////////////////////////////// number of islands
        for (int i = 0; i < 100; i++) {
            ImageView img = loadIsland(prev,477,true);
            // load trees at 60% chance
            if(ThreadLocalRandom.current().nextInt(0,5) < 3) {
                ImageView treeImg = loadTrees((int)img.getX());
                scenePane.getChildren().add(2,treeImg);
                objects.get("trees").add(treeImg);
            }
            // load orcs at 40% chance
            if(ThreadLocalRandom.current().nextInt(0,5) < 2) {
                ImageView orcImg = loadOrcs((int)img.getX());
                scenePane.getChildren().add(zIdx,orcImg);
                objects.get("orcs").add(orcImg);
            }
            // load chests at chance of 20 %
            if(ThreadLocalRandom.current().nextInt(0,5) < 1 && img.getFitWidth() > 180) {
                ImageView chestImg = loadChest((int) img.getX());
                scenePane.getChildren().add(2,chestImg);
                objects.get("chests").add(chestImg);
            }
            // load tnt at 40% chance
            if(ThreadLocalRandom.current().nextInt(0,5) < 2 && img.getFitWidth() > 160) {
                ImageView tntImg = loadTNT((int) img.getX());
                scenePane.getChildren().add(2,tntImg);
                objects.get("tnts").add(tntImg);
            }
            prev = (int)img.getX() + (int)(img.getFitWidth() / 2) + ThreadLocalRandom.current().nextInt(-4,90);
            scenePane.getChildren().add(img);
            objects.get("islands").add(img);
        }
        // for all orcs jump transition
        for (ImageView orcImg : objects.get("orcs")) {
            jump(orcImg,1000,-120,true,ThreadLocalRandom.current().nextInt(500,1000));
        }

        for (ImageView tntImg : objects.get("tnts")) {
            homeCtrl.tntTrans(tntImg);
        }

        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean isOpen = false;
                int img = -1;
                for (ImageView orcImg : objects.get("orcs")) {
                    orcCollisionChk(orcImg,hero);
                }
                for (ImageView chestImg : objects.get("chests")) {
                    isOpen = chestCollisionChk(chestImg,hero);
                    img = objects.get("chests").indexOf(chestImg);
                }
                for (ImageView tntImg : objects.get("tnts")) {
                    tntCollisionChk(tntImg,hero);
                }
                if (isOpen) {
                    objects.get("openChests").add(objects.get("chests").get(img));
                    objects.get("chests").remove(img);
                }
                heroFallChk(hero);
                for (ImageView orc : objects.get("orcs")) {
                    orcFallChk(orc);
                }
            }
        };
        collisionTimer.start();
    }

    private ImageView loadTNT(int X) {
        ImageView img = new ImageView(assets.get("Tnt")[0]);
        img.setFitWidth(32);
        img.setFitHeight(40);
        img.setY(440);
        img.setX(X+ThreadLocalRandom.current().nextInt(75,90));
        return img;
    }

    private ImageView loadChest(int X) {
        ImageView img = new ImageView(assets.get("Chests")[0]);
        img.setFitWidth(46);
        img.setFitHeight(28);
        img.setY(449);
        img.setX(X+ThreadLocalRandom.current().nextInt(105,140));
        return img;
    }

    private ImageView loadOrcs(int X) {
        int OrcNum = ThreadLocalRandom.current().nextInt(0, 2);
        ImageView img;
        if (OrcNum == 0) {
            img = new ImageView(assets.get("Orcs")[0]);
        } else {
            img = new ImageView(assets.get("Orcs")[3]);
        }
        img.setPreserveRatio(true);
        img.setX(X+ThreadLocalRandom.current().nextInt(20,50));
        img.setFitWidth(38);
        img.setY(441);
        return img;
    }

    private ImageView loadTrees(int X) {
        int treeNum = ThreadLocalRandom.current().nextInt(0, assets.get("Trees").length);
        ImageView img = new ImageView(assets.get("Trees")[treeNum]);
        img.setPreserveRatio(true);
        img.setX(X+ThreadLocalRandom.current().nextInt(0,40));
        if(treeNum == 1) {
            img.setFitWidth(31);
            img.setY(408);
        } else {
            img.setFitWidth(43);
            img.setY(419);
        }
        return img;
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
        return img;
    }
    static int answer = 0;
    @FXML
    private void pauseEvent(MouseEvent e) throws IOException {
        answer = display();
        if (answer == 1) {
            // restart
            GameRecords.CommonStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("gamescreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            //Stage GameRecords.CommonStage = new Stage();//(scene);
            GameRecords.CommonStage.setResizable(false);
            GameRecords.CommonStage.setScene(scene);
            GameRecords.CommonStage.show();
        } else if (answer == 2) {
            // save game
        } else if (answer == 3) {
            // home screen
            GameRecords.CommonStage.close();
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
        } else {
            // no option choose
        }
    }

    private static void setButton(Button node, int layoutX, int layoutY, int fitWidth, int fitHeight) {
        node.setLayoutX(layoutX);
        node.setLayoutY(layoutY);
        node.setPrefHeight(fitHeight);
        node.setPrefWidth(fitWidth);
    }
    private static void setImage(ImageView node, int layoutX, int layoutY, int fitWidth, int fitHeight) {
        node.setLayoutX(layoutX);
        node.setLayoutY(layoutY);
        node.setPreserveRatio(true);
        node.setFitHeight(fitHeight);
        node.setFitWidth(fitWidth);
    }

    public static int display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pause Screen");
        Button restart = new Button("Restart");
        setButton(restart,171,119,182,49);
        ImageView restImg = new ImageView(new Image(String.valueOf(Gamescreen.class.getResource("pause/reset.png"))));
        setImage(restImg,53,104,78,81);
        Button saveGame = new Button("Save Game");
        setButton(saveGame,171,252,182,49);
        ImageView saveImg = new ImageView(new Image(String.valueOf(Gamescreen.class.getResource("pause/diskette.png"))));
        setImage(saveImg,53,236,78,78);
        Button homeScreen = new Button("Home Screen");
        setButton(homeScreen,171,395,182,49);
        ImageView homeImg = new ImageView(new Image(String.valueOf(Gamescreen.class.getResource("pause/homepage.png"))));
        setImage(homeImg,60,395,78,78);
        // for background
        ImageView backImg = new ImageView(new Image(String.valueOf(Gamescreen.class.getResource("pause/pause.png"))));
        backImg.setFitHeight(550);
        backImg.setFitWidth(400);

        restart.setOnAction(e -> {
                answer = 1;
                stage.close();
            }
        );

        saveGame.setOnAction(e -> {
                answer= 2;
                stage.close();
            }
        );
        homeScreen.setOnAction(e -> {
                answer= 3;
                stage.close();
            }
        );

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(0,backImg);
        anchorPane.getChildren().addAll(saveGame,saveImg,homeScreen,homeImg,restart,restImg);
        Scene scene = new Scene(anchorPane, 400, 550);
        scene.getStylesheets().add(String.valueOf(Gamescreen.class.getResource("button.css")));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.showAndWait();

        return answer;
    }


    @FXML
    private void clickAnimationHandler(MouseEvent e) {
        int score = Home.player.getScore();
        score+=5;
        Home.player.SetScore(score);
        heroTran.pause();
        psuedoForward(hero,120,1,120,false,0);
        for (String imgName : objects.keySet()) {
            objects.get(imgName).forEach(img -> transAnimation(img, 200,1,-120,false,100));
        }// 283
        psuedoForward(hero, 200,1,0,false,100);
        heroTran.setDuration(Duration.millis(700));
        heroTran.play();
        scoreLbl.setText(String.valueOf(Home.player.getScore()));
    }

    private void orcCollisionChk(ImageView a, Rectangle b) {
        if (a.getBoundsInParent().intersects(b.getBoundsInParent())) {
            // below the orc
            if ((a.getBoundsInParent().getCenterY() + (a.getFitHeight()/2)) == (b.getBoundsInParent().getCenterY() - 19)) {
                heroTran.stop();
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(hero);
                translate.setDuration(Duration.millis(700));
                translate.setCycleCount(1);
                translate.setByY(1000);
                translate.setAutoReverse(false);
                translate.play();
            }
            heroTran.pause();
            psuedoForward(b,200,1,-60,false,0);
            transAnimation(a, 200,1,80,false,0);
            heroTran.play();
        }
    }
    private boolean chestCollisionChk(ImageView a, Rectangle b) {
        if (a.getBoundsInParent().intersects(b.getBoundsInParent())) {
            a.setImage(assets.get("Chests")[1]);
            return true;
        }
        return false;
    }
    private void tntCollisionChk(ImageView a, Rectangle b) {
        if (a.getBoundsInParent().intersects(b.getBoundsInParent())) {
            ImageView boomImg = new ImageView(new Image(String.valueOf(getClass().getResource("boom.png"))));
            boomImg.setPreserveRatio(true);
            boomImg.setFitWidth(80);
            boomImg.setX(a.getBoundsInParent().getCenterX()+55);
            boomImg.setY(a.getBoundsInParent().getCenterY()-45);
            scenePane.getChildren().add(boomImg);
            FadeTransition ft = fade(boomImg,0.0,1.0,1,1000,false);
            heroTran.pause();
            psuedoForward(b,100,1,-1,false,0);
            transAnimationToY(a,400,2,-80,true,0);
            transAnimation(a, 800,1,100,false,0);
            heroTran.play();
            ft.setOnFinished(event -> {
//                killInRange(boomImg.getBoundsInParent().getCenterX(),boomImg.getBoundsInParent().getCenterY(),50);
                scenePane.getChildren().remove(a);
                scenePane.getChildren().remove(boomImg);
                objects.get("tnts").remove(a);
            });
        }
    }

    private void killInRange(double x, double y, int range) {
        System.out.println(hero.getBoundsInParent().getCenterX()+", range = "+(x-range));
        if ((hero.getBoundsInParent().getCenterX() > x - range || hero.getBoundsInParent().getCenterX() < x + range)
                && (hero.getBoundsInParent().getCenterY() < y - range || hero.getBoundsInParent().getCenterY() > y + range)) {
            heroTran.stop();
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(hero);
            translate.setDuration(Duration.millis(700));
            translate.setCycleCount(1);
            translate.setByY(1000);
            translate.setAutoReverse(false);
            translate.play();
        }
        for (ImageView orcImg : objects.get("orcs")) {
            if ((orcImg.getBoundsInParent().getCenterX() > x - range || orcImg.getBoundsInParent().getCenterX() < x + range)
                    && (orcImg.getBoundsInParent().getCenterY() < y - range || orcImg.getBoundsInParent().getCenterY() > y + range)) {
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(orcImg);
                translate.setDuration(Duration.millis(700));
                translate.setCycleCount(1);
                translate.setByY(1000);
                translate.setAutoReverse(false);
                translate.play();
                scenePane.getChildren().remove(orcImg);
            }
        }
    }

    private void heroFallChk(Rectangle b) {
        boolean isAboveIsland = false;
        boolean isHeroLow = false;

        Bounds boundHero = b.getBoundsInParent();
        for (ImageView a : objects.get("islands")) {
            Bounds boundIsland = a.getBoundsInParent();
            if (boundHero.getCenterX() > (boundIsland.getCenterX() - (a.getFitWidth()/2)) && boundHero.getCenterX() < (boundIsland.getCenterX() + (a.getFitWidth()/2))) {
                isAboveIsland = true;
            }
        }
        if (boundHero.getCenterY() >= 458) {
            isHeroLow = true;
        }
        if ((!isAboveIsland) && isHeroLow) {
            heroTran.stop();
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(hero);
            translate.setDuration(Duration.millis(700));
            translate.setCycleCount(1);
            translate.setByY(1000);
            translate.setAutoReverse(false);
            translate.play();
        }
    }

    private void orcFallChk(ImageView b) {
        boolean isAboveIsland = false;
        boolean isOrcLow = false;

        Bounds boundOrc = b.getBoundsInParent();
        for (ImageView a : objects.get("islands")) {
            Bounds boundIsland = a.getBoundsInParent();
            if (boundOrc.getCenterX() > (boundIsland.getCenterX() - (a.getFitWidth()/2)) && boundOrc.getCenterX() < (boundIsland.getCenterX() + (a.getFitWidth()/2))) {
//                System.out.println("Hello");
                isAboveIsland = true;
            }
        }
        if (boundOrc.getCenterY() >= 458) {
//            System.out.println(boundOrc.getCenterY());
            isOrcLow = true;
        }
        if ((!isAboveIsland) && isOrcLow) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(b);
            translate.setDuration(Duration.millis(500));
            translate.setCycleCount(1);
            translate.setByY(1000);
            translate.setAutoReverse(false);
            translate.play();
        }
    }

    private HashMap<String,Image[]> loadAssets() {
        HashMap<String,Image[]> assets = new HashMap<>();
        assets.put("Rocks",new Image[4]);
        assets.put("Coin",new Image[] {new Image(String.valueOf(getClass().getResource("assets/Coin.png")))});
        assets.put("Islands",new Image[11]);
        assets.put("Orcs",new Image[6]);
        assets.put("Tnt", new Image[] {new Image(String.valueOf(getClass().getResource("assets/TNT.png")))});
        assets.put("Chests",new Image[] {new Image(String.valueOf(getClass().getResource("assets/ChestClosed.png"))),
                                         new Image(String.valueOf(getClass().getResource("assets/ChestOpen.png")))});
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
        for (int i = 0; i < 4; i++) {
            assets.get("Trees")[i] = new Image(String.valueOf(getClass().getResource("assets/Tree"+(i+1)+".png")));
        }
        return assets;
    }
    private void pause(){

    }

    private void play_audio(){
        AudioClip note = new AudioClip(Objects.requireNonNull(this.getClass().getResource("Udd_Gaye.mp3")).toString());
        note.setCycleCount(AudioClip.INDEFINITE);
        note.setVolume(0.09);
        note.play();
    }
}
