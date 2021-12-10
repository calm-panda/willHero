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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genCloud(-2200,new int[] {0,1000,3000});

        TranslateTransition translate7 = new TranslateTransition();
        translate7.setNode(hero);
        translate7.setDuration(Duration.millis(450));
        translate7.setCycleCount((TranslateTransition.INDEFINITE));
        translate7.setByY(-130);
        translate7.setAutoReverse(true);
        translate7.play();
        TranslateTransition translate8 = new TranslateTransition();
        translate8.setNode(Orc1);
        translate8.setDuration(Duration.millis(850));
        translate8.setCycleCount((TranslateTransition.INDEFINITE));
        translate8.setByY(-130);
        translate8.setAutoReverse(true);
        translate8.play();
        TranslateTransition translate9 = new TranslateTransition();
        translate9.setNode(RedOrc2);
        translate9.setDuration(Duration.millis(800));
        translate9.setCycleCount((TranslateTransition.INDEFINITE));
        translate9.setByY(-130);
        translate9.setAutoReverse(true);
        translate9.play();
        TranslateTransition translate10 = new TranslateTransition();
        translate10.setNode(RedOrc1);
        translate10.setDuration(Duration.millis(650));
        translate10.setCycleCount((TranslateTransition.INDEFINITE));
        translate10.setByY(-60);
        translate10.setAutoReverse(true);
        translate10.play();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(tnt);
        scale.setDuration(Duration.millis(700));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.setAutoReverse(true);
        scale.play();
    }
}