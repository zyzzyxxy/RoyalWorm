package Canvas;
/**
 * A panel used as a canvas to draw the gameWorld and the objects in it
 * @author Anton Eliasson Gustafsson
 * @version 2019-03-09
 */

import javax.swing.*;

import GameHandling.GameEngine;
import Positions.Change;
import Positions.Position;
import src.Constants;

import java.awt.*;
import java.awt.Graphics;
import java.util.List;

public class GameCanvas extends JPanel {

    //public Direction direction;
    public List<Change> changes;
    public int paintCounter=0;

    /**
     * Constructor for GameCanvas. It initiate a reference to changes and sets canvas size and background.
     */
    public GameCanvas()
    {
        changes = GameEngine.changes;
        setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Color.black);
        repaint();
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
            drawChanges(g);
        }
    }

    /**
     * This method is a brute force that paints the entire canvas according to GameWorld.
     * Should not be used if not necessary, as it is inefficient.
     *
     * @param g the graphics that will be used
     */
    public void drawWorld(Graphics g)
    {
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
            	drawObject(GameEngine.GameWorld[i][j], new Position(i,j), g);
            }
    }

    /**
     * Only draws the changes that has been provided. A better way to go for painting than drawWorld.
     *
     * @param g the graphics that will be used
     */
    public void drawChanges(Graphics g)
    {
        if(!changes.isEmpty())
        {
            for (Change ch :changes) {
                drawObject(ch.getType(),new Position(ch.getX(),ch.getY()),g);
            }

        }
        GameEngine.changes.clear();
    }

    /**
     * Draws the object given to it at it´s place
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
            case 'l': //l as in lightning
                GameGraphics.drawLightninh(p,g);
                break;
            case 'g': //g as in ghost
                GameGraphics.drawGhost(p,g);
                break;
            case 'b':
            	GameGraphics.drawBullet(p,g);
            	break;
            case 'p': //p as in pistol
            	GameGraphics.drawGun(p,g);
            	break;
        }
    }
}
