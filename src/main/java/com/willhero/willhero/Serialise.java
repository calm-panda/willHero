package com.willhero.willhero;
import java.io.*;

public class Serialise {

    public void AddObj(Player obj, String filename) throws IOException {
        //out = null;

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/panda/IdeaProjects/willHero/src/main/resources/com/willhero/willhero/Saved Games/" + filename + ".txt"));
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player GetData(String filename) throws IOException {
        Player obj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/panda/IdeaProjects/willHero/src/main/resources/com/willhero/willhero/Saved Games/" + filename + ".txt"));
            obj = (Player) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("User Doesn't Exist Try New Game!!!!!\n");
        }
        if (obj != null)
            return obj;
        return obj;
    }
}
