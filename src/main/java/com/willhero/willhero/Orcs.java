package com.willhero.willhero;

import javafx.scene.image.ImageView;

public class Orcs extends GameElements implements Obstacle,animate {
    private int jumpY, health;

    public Orcs(ImageView img, Coordinate _p, Double _xS, Double _yS, int _jY, int _h) {
        super(img, _p, _xS, _yS);
        this.jumpY = _jY;
        this.health = _h;

    }

    // fix it
    @Override
    public <Player> void interaction(Player player) {
        // interact with orc
    }

    // fix it
    @Override
    public <Player> void gameOver(Player player) {
        // game over code
    }
}
