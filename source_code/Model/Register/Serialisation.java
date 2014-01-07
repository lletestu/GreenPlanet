/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 23/04/13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Register;

import GreenPlanet.Model.Player.PlayerData;

import java.io.*;
import java.util.HashMap;

/**
 * Class allows to serialize all data register for one game
 */
public class Serialisation {
    /**
     * Name of the file serialized
     */
    private static String name = ManagerDirectory.getAbsolutePath()+"DataSerialisation";

    /**
     * This method allows to serialize an hash map
     * @param playersDataGame hash map that represents all data registered for one game
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O
     */
    public static void serialisation(HashMap<Integer,HashMap<Integer,PlayerData>> playersDataGame) throws IOException{

        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
                oos.writeObject(playersDataGame);
                oos.flush();
        } finally {
            try {
                oos.close();
            } finally {
                fos.close();
            }

        }
    }

    /**
     * This method allows to de-serialize an file
     * @return Object hash map that store all information for one game
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O
     * @throws ClassNotFoundException thown when an application tries to load a class which not found
     */
    public static HashMap<Integer,HashMap<Integer,PlayerData>>  deserialisation()throws IOException, ClassNotFoundException{
        HashMap<Integer,HashMap<Integer,PlayerData>> playersDataGame = new HashMap<Integer, HashMap<Integer, PlayerData>>();

        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {

             playersDataGame=((HashMap<Integer,HashMap<Integer,PlayerData>>)ois.readObject());

        } finally {
            try {
                ois.close();
            } finally {
                fis.close();
            }

        }
        return playersDataGame;

    }
}
