import java.awt.*;

public class Constants {

    public static int boardWidth = 800;
    public static int boardHeight = 600;
    public static int wormsize = 10;
    public static int gameConstant = 10; //using to scale world and draw figures
    public static int wormspeed = 5;

    public static int worldWidth = boardWidth / wormsize;
    public static int worldHeight = boardHeight / wormsize;

    public static int startingLives = 3;

    //Color
    public static Color backgroundColor = Color.BLACK;
    public static Color appleColor = Color.RED;


    public static int serverPort = 1234;
    public static int clientPort = 1234;


}
