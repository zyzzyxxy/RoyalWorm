import java.awt.*;

public class Constants {

    //GameArgs
    public static int boardWidth = 800;
    public static int boardHeight = 600;
    public static int gameConstant = 10; //using to scale world and draw figures
    public static int APPLESPAWN = 500;
    public static int SLOWSPAWN = 50;
    public static int GAMESPEED = 5;
    public static int GENERALSPAWNRATE = 20;
    public static boolean WallCollision = true;



    //WormArgs
    public static int wormsize = 10;
    public static int wormspeed = 12;
    public static int wormStartingLength = 8;
    public static int startingLives = 3;


    public static int worldWidth = boardWidth / wormsize;
    public static int worldHeight = boardHeight / wormsize;

    //Color
    public static Color backgroundColor = Color.BLACK;
    public static Color appleColor = Color.RED;
    public static Color p1Color = Color.blue;
    public static Color p2Color = Color.green;
    public static Color p3Color = Color.yellow;
    public static Color p4Color = Color.cyan;


    public static int serverPort = 1234;
    public static int clientPort = 1234;
}
