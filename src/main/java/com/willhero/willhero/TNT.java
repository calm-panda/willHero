package com.willhero.willhero;

import javafx.scene.image.ImageView;

public class TNT extends GameElements implements Obstacle{
    /// set tnt blast range
    private static final Double range = (double)50;

    public TNT(ImageView img, Coordinate _p, Double _xS, Double _yS) {
        super(img ,_p, _xS, _yS);
    }

    // fix it
    @Override
    public <Player> void interaction(Player player) {
        // gui code changes with respect to tnt
    }

    // fix it
    @Override
    public <Player> void gameOver(Player player) {
        // executes on game over with tnt
    }
}
