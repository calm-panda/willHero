package com.willhero.willhero;

public class Hero {
    int JumpX = -130;
    int JumpY;
    void SetJumpx(int x){
        JumpX = x;
    }
    void SetJumpY(int y){
        JumpY = y;
    }
    int getJumpX(){
        return JumpX;
    }
    int getJumpY(){
        return JumpY;
    }
}
