package src;
import java.awt.*;
/**
 * 
 * This class has stored public constants used in the game. 
 * @author Johan Ericsson
 * @author Anton Eliasson Gustafsson
 * @author Martin Hagentoft
 * @author Jonathan Uhre
 * @version 2019-03-01
 */
public class Constants {

    public static int WALL_SPAWN_SPEED = 1000;
	//GameArgs
    public static int boardWidth = 800;
    public static int boardHeight = 600;
    public static int gameConstant = 10; //using to scale world and draw figures
    public static int APPLESPAWN = 50;
    public static int SLOWSPAWN = 50;
    public static int GAMESPEED = 5;
    public static int GENERALSPAWNRATE = 20;

    //WormArgs
    public static int wormsize = 10;
    public static int wormspeed = 12;
    public static int wormStartingLength = 8;
    public static int startingLives = 3;
    public static int allowedFire = 200;

    //Dynamics
    public static int ghostSpeed = 20;
    public static int ghostSpawn = 200;
    public static int bulletSpeed = 5;
    
    public static int worldWidth = boardWidth / wormsize;
    public static int worldHeight = boardHeight / wormsize;

    //Color
    public static Color backgroundColor = Color.BLACK;
    public static Color appleColor = Color.RED;
    public static Color p1Color = Color.blue;
    public static Color p2Color = Color.green;
    public static Color p3Color = Color.yellow;
    public static Color p4Color = Color.cyan;
    public static Color p5Color = Color.white;


    public static int serverPort = 1234;
    public static int clientPort = 1234;
}
