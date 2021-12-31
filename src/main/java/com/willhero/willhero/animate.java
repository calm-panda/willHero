package com.willhero.willhero;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public interface animate {
    public default void transAnimation(ImageView img, int duration, int times, int byX, boolean rev, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
        translate.setByX(byX);
        translate.setAutoReverse(rev);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.setInterpolator(Interpolator.EASE_IN);
        seqTransition.play();
    }

    public default void psuedoForward(Rectangle img, int duration, int times, int toX, boolean rev, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
        translate.setToX(toX);
        translate.setAutoReverse(rev);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(pause)),translate);
        seqTransition.setInterpolator(Interpolator.EASE_IN);
        seqTransition.play();
    }

    public default void jump(ImageView img, int duration, int byY, boolean isRev) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(byY);
        translate.setAutoReverse(isRev);
        translate.play();
    }

    public default TranslateTransition jump(Rectangle img, int duration, int byY, boolean isRev) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(byY);
        translate.setAutoReverse(isRev);
        translate.play();
        return translate;
    }
}
