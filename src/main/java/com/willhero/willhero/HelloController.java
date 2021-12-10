package com.willhero.willhero;

import javafx.animation.ScaleTransition;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //translate
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(cloud1);
        translate.setDuration((Duration.millis(900)));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByX(-1000);
       // translate.setAutoReverse(true);
        translate.play();
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(cloud2);
        translate2.setDuration((Duration.millis(1000)));
        translate2.setCycleCount((TranslateTransition.INDEFINITE));
        translate2.setByX(-1000);
        //translate2.setAutoReverse(true);
        translate2.play();
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(cloud3);
        translate3.setDuration((Duration.millis(1200)));
        translate3.setCycleCount((TranslateTransition.INDEFINITE));
        translate3.setByX(-1000);
        //translate3.setAutoReverse(true);
        translate3.play();
        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cloud4);
        translate4.setDuration((Duration.millis(1500)));
        translate4.setCycleCount((TranslateTransition.INDEFINITE));
        translate4.setByX(-1000);
        //translate4.setAutoReverse(true);
        translate4.play();
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(cloud5);
        translate5.setDuration((Duration.millis(1300)));
        translate5.setCycleCount((TranslateTransition.INDEFINITE));
        translate5.setByX(-1000);
        //translate5.setAutoReverse(true);
        translate5.play();
        TranslateTransition translate6 = new TranslateTransition();
        translate6.setNode(cloud6);
        translate6.setDuration((Duration.millis(1200)));
        translate6.setCycleCount((TranslateTransition.INDEFINITE));
        translate6.setByX(-1000);
        //translate6.setAutoReverse(true);
        translate6.play();
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