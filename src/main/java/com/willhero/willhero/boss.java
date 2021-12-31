package com.willhero.willhero;

import javafx.scene.image.ImageView;

public class boss extends Orcs implements animate{

    public boss(ImageView img, Coordinate _p, Double _xS, Double _yS, int _jY) {
        super(img, _p, _xS, _yS, _jY, 100);
    }
}
