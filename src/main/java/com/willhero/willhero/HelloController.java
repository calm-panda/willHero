package com.willhero.willhero;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML private ImageView cloud1, cloud2, cloud3, cloud4, cloud5, cloud6;
    @FXML private ImageView Orc1, RedOrc1, RedOrc2;
    @FXML private ImageView hero;
    @FXML private ImageView tnt;
    @FXML protected void onHelloButtonClick() {
        welcomeText.setText("Hello World!!...\n");
    }

    private void clouds(ImageView cloud, int toX, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(cloud);
        translate.setDuration((Duration.millis(10000)));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setToX(toX);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    private void genCloud(int toX, int[] pause) {
        clouds(cloud1,toX,pause[0]);
        clouds(cloud2,toX,pause[0]);
        clouds(cloud3,toX,pause[1]);
        clouds(cloud4,toX,pause[1]);
        clouds(cloud5,toX,pause[2]);
        clouds(cloud6,toX,pause[2]);
    }

    private void jump(ImageView img, int transTime, int toY, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(transTime));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(toY);
        translate.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.play();
    }

    private void tntTrans(ImageView img) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(tnt);
        scale.setDuration(Duration.millis(900));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setByX(0.12);
        scale.setByY(0.12);
        scale.setAutoReverse(true);
        scale.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //clouds
        genCloud(-2200,new int[] {0,1000,3000});
        //hero
        jump(hero,850,-130,0);
        //orcs
        int pause = 500;
        jump(Orc1,1000,-130,pause);
        jump(RedOrc1,950,-90,pause);
        jump(RedOrc2,750,-60,pause);
        //tnt
        tntTrans(tnt);
    }
}