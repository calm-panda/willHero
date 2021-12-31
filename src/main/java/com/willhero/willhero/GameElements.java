package com.willhero.willhero;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameElements {
    private final Coordinate point;
    private static int count;
    private final int id;
    private Double xSpeed;
    private Double YSpeed;
    private ImageView img;

    public GameElements(ImageView img, Coordinate _p, Double _xS, Double _yS) {
        this.id = count;
        this.xSpeed = _xS;
        this.YSpeed = _yS;
        this.point = _p;
        this.img = img;
        count++;
    }

    public Coordinate getPoint() {
        return this.point;
    }

    public Double getxSpeed() {
        return this.xSpeed;
    }

    public Double getYSpeed() {
        return this.YSpeed;
    }

    public void setxSpeed(Double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(Double YSpeed) {
        this.YSpeed = YSpeed;
    }

    // fix it
    public <Player> void interaction(Player player) {
        // gui changes code
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        GameElements that = (GameElements) o;
        return id == that.id && this.point.equals(that.point) && Objects.equals(xSpeed, that.xSpeed) && Objects.equals(YSpeed, that.YSpeed);
    }
}
