package Canvas;
/**
 * A panel used as a canvas to draw the clientWorld and the objects in it
 * @author Anton Eliasson Gustafsson
 * @author Johan Ericsson
 * @author Jonathan Uhre
 * @version 2019-03-09
 */

import javax.swing.*;

import Positions.Position;
import src.Constants;

import java.awt.*;
import java.awt.Graphics;
import java.util.Arrays;

public class ClientCanvas extends JPanel {

    private char[][] clientWorld;
    private int paintCounter = 0;

    /**
     * Constructor for ClientCanvas. Copies world to clientWorld and sets canvas size and background.
     * 
     * @param world The gameWorld received from host. It has to match the size of the clientWorld (Constants.worldWidth x Constants.worldHeight).
     */
    public ClientCanvas(char[][] world)
    {
    	clientWorld = new char[Constants.worldWidth][Constants.worldHeight];
        updateClientWorld(world);
        setSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);
    }

    /**
     * Updates the clientWorld by copying world.
     * 
     * @param world	The gameWorld received from host.
     */
    public void updateClientWorld(char[][] world)
    {
    	for (int y = 0; y < Constants.worldHeight; y++) {
    		for (int x = 0; x < Constants.worldWidth; x++) {
    			clientWorld[x][y] = world[x][y];
    		}		
    	}
    }

    /**
     * @param x	The X coordinate 
     * @param y The Y coordinate
     * @return The char value of the given position.
     */
    public char getClientWorld(int x, int y) {
    	return clientWorld[x][y];
    }
    
    /**
     * The method used when painting
     *
     * @param g The Graphics that will be used
     */
    @Override
    public void paint(Graphics g) {
        if (paintCounter < 3) {
            drawWorld(g);
            paintCounter++;
        } else {
            drawWorld(g);
        }
    }
    
    /**
     * This method is a brute force that paints the entire canvas according to GameWorld.
     * Should not be used if not necessary, as it is inefficient.
     *
     * @param g The Graphics that will be used
     */
    public void drawWorld(Graphics g)
    {
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
                    drawObject(clientWorld[i][j], new Position(i,j), g);
            }
    }

    /**
     * Draws the object given to it at it�s place
     *
     * @param c the character is the type of object
     * @param p the position to paint
     * @param g the graphics that will be used
     */
    public void drawObject(char c, Position p, Graphics g)
    {
        switch (c)
        {
            case 'a':
                GameGraphics.drawApple(p, g);
                break;
            //For players
            case '0':
                GameGraphics.erase(p,g);
                break;
            case '1':
                GameGraphics.drawPlayer(false,1,p,g);
                break;
            case '2':
                GameGraphics.drawPlayer(false,2,p,g);
                break;
            case '3':
                GameGraphics.drawPlayer(false,3,p,g);
                break;
            case '4':
                GameGraphics.drawPlayer(false,4,p,g);
                break;
            case '5':
                GameGraphics.drawPlayer(false,5,p,g);
                break;
            case 'w':
                GameGraphics.drawWall(p,g);
                break;
            case 's':
                GameGraphics.drawSuperApple(p,g);
                break;
            case 'l':
                GameGraphics.drawLightninh(p,g);
                break;
            case 'g':
                GameGraphics.drawGhost(p,g);
                break;
            case 'p':
                GameGraphics.drawGun(p,g);
                break;
            case 'b':
                GameGraphics.drawBullet(p,g);
                break;
        }
    }
}
