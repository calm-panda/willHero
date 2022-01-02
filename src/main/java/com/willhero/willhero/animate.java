package com.willhero.willhero;

import javafx.animation.*;
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

    public default void transAnimationToY(ImageView img, int duration, int times, int toY, boolean rev, int pause) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(times);
        translate.setToY(toY);
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

    public default void jump(ImageView img, int duration, int byY, boolean isRev, int delay) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount((TranslateTransition.INDEFINITE));
        translate.setByY(byY);
        translate.setAutoReverse(isRev);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(delay)),translate);
        seqTransition.setInterpolator(Interpolator.EASE_OUT);
        seqTransition.play();
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

    public default FadeTransition fade(ImageView img, double fadeFrom, double fadeTo, int cycle, int delay, boolean rev) {
        FadeTransition ft = new FadeTransition(Duration.millis(400), img);
        ft.setFromValue(fadeFrom);
        ft.setToValue(fadeTo);
        ft.setCycleCount(cycle);
        ft.setAutoReverse(rev);
        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(delay)),ft);
        seqTransition.play();
        return ft;
    }
}