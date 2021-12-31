package com.willhero.willhero;

import javafx.scene.image.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class Chests extends GameElements {
    private int weaponNo;
    private int coins;
    private final int rewards;

    public Chests(ImageView img, Coordinate _p, Double _xS, Double _yS) {
        super(img, _p, _xS, _yS);
        this.rewards = ThreadLocalRandom.current().nextInt(0,2);
        if (this.rewards == 0) {
            this.weaponNo = ThreadLocalRandom.current().nextInt(0,2);
            this.coins = 0;
        } else {
            this.coins = ThreadLocalRandom.current().nextInt(10,41);
            this.weaponNo = -1;
        }
    }

    // fix it
    @Override
    public <Player> void interaction(Player player) {
        if (coins == 0) {
            // add weapons
        } else {
            // upgrade or add weapon
        }
        // changes in chest gui
    }
}
