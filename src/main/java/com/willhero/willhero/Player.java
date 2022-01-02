package com.willhero.willhero;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    String PlayerName, date;
    Gamescreen currentState;
    int CoinsNum;
    int Score;
    ArrayList<Weapon> weapon = new ArrayList<Weapon>();
    //GameElements hero;   To be created
    String getName(){
        return PlayerName;
    }
    int getCoinsNum(){
        return CoinsNum;
    }
    void setPlayerName(String name) {   PlayerName = name; }
    void setCoinsNum(int num){
        CoinsNum = num;
    }
    void SetScore(int sc){
        Score = sc; //Setting Score
        Date date = new Date();
        this.date = date.toString();
    }
    void setCurrentState(Gamescreen obj)  {  currentState = obj;   }
    int getScore(){
        return Score;
    }
//    Hero getHero(){
//        return hero;
//    }
    ArrayList<Weapon> getWeapon(){
        return weapon;
    }
    

}
