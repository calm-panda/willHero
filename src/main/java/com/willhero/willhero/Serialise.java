package com.willhero.willhero;
import java.io.*;

public class Serialise {
    ObjectOutputStream out = null;
    public void AddObj(Player obj, String filename) throws IOException {
        try {
            out = new ObjectOutputStream(new FileOutputStream("/Saved Games/"+filename+".txt"));
            out.writeObject(obj);
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
    public void GetData(String filename) throws IOException {
        Player obj = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("/Saved Games/"+filename+".txt"));
            obj = (Player)in.readObject();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            System.out.println("User Doesn't Exist Try New Game!!!!!\n");
        }
        finally {
            in.close();
        }
        if(obj !=  null)
            System.out.println("Name: "+obj.PlayerName+", Score: "+obj.Score+", Time: "+obj.date);
    }
}
