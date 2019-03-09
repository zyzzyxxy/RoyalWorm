/**
 * A panel used as a canvas to draw the gameworld and the objects in it
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.List;

public class GameCanvas extends JPanel {

    //public Direction direction;
    public List<Change> changes;
    public int paintCounter=0;

    public GameCanvas()
    {
        changes = GameEngine.changes;
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        this.setBackground(Color.black);
        this.repaint();
    }

    /**
     * The method used when painting
     *
     * @param g the graphics that will be used
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
     * This method is a brute force that checks all positions in the world every time and paint the world accordingly.
     * Should not be used if not necessary, as it is inefficient.
     *
     * @param g the graphics that will be used
     */
    public void drawWorld(Graphics g)
    {
        //Brute Force
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
                if(GameEngine.GameWorld[i][j]!='0')
                    drawObject(GameEngine.GameWorld[i][j], new Position(i,j), g);
                else
                    drawObject('0', new Position(i,j), g);

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
