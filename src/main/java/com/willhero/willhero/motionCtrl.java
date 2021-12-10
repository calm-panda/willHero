package com.willhero.willhero;

public class motionCtrl {

    final int fromX,fromY,toX,toY;
    final boolean isRep;

    public  motionCtrl(int _fx, int _fy, int _tx, int _ty,boolean _isRep) {
        this.fromX = _fx;
        this.fromY = _fy;
        this.toX = _tx;
        this.toY = _ty;
        this.isRep = _isRep;
    }


}
