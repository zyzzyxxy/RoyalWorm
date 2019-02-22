import java.awt.*;

public class Constants {

    public static int boardWidth = 800;
    public static int boardHeight = 600;

    public static int gameConstant = 10; //using to scale world and draw figures

    public static int APPLESPAWN = 500;


    //WormArgs
    public static int wormsize = 10;
    public static int wormspeed = 10;
    public static int wormStartingLength = 8;


    public static int worldWidth = boardWidth / wormsize - 1;
    public static int worldHeight = boardHeight / wormsize - 1;

    public static int startingLives = 3;

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
