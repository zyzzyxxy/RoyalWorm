package Canvas;
/**
 * @author Johan Ericsson
 * @author Anton Eliasson Gustafsson
 * @Version 2019-03-06
 * This Class takes care of the graphics. May be modified for nicer graphics.
 */

import java.awt.*;

import Positions.Position;
import src.Constants;

public class GameGraphics {

    /**
     * Draws an apple.
     *
     * @param p for Position. The position to where to draw
     * @param graphics the graphics that will be used
     * @return Updated Graphics
     */
    public static Graphics drawApple(Position p, Graphics graphics) {
        int constant = Constants.gameConstant;
        graphics.setColor(Constants.appleColor);

        graphics.fillOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.setColor(Color.GREEN);
        //TODO make details better :)
        //Draw details, just for fun
        for (int i = 0; i < 5; i++) {
            graphics.drawArc(p.getX() * constant-2 , p.getY() * constant - i+5, 5 + i, 5, 0, 90);
        }
        return graphics;
    }

    /**
     * Draws an super-apple. Lika apple but other colors.
     *
     * @param p for Position. The position to where to draw
     * @param graphics the graphics that will be used
     * @return Updated Graphics
     */
    public static Graphics drawSuperApple(Position p, Graphics graphics) {
        int constant = Constants.gameConstant;
        graphics.setColor(Color.magenta);

        graphics.fillOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            graphics.drawArc(p.getX() * constant-2 , p.getY() * constant - i+5, 5 + i, 5, 0, 90);
        }
        return graphics;
    }

    /**
     * Draws the worm of a player
     *
     * @param head as a boolean. This can be used to paint the head of the worm
     * @param player the player to be drawn.
     * @param p for position, where to draw
     * @param graphics the graphics that will be used
     * @return Updated Graphics
     */
    public static Graphics drawPlayer(boolean head, int player, Position p, Graphics graphics) {
        if (player == 1)
            graphics.setColor(Constants.p1Color);
        else if (player == 2)
            graphics.setColor(Constants.p2Color);
        else if (player == 3)
            graphics.setColor(Constants.p3Color);
        else if (player == 4)
            graphics.setColor(Constants.p4Color);
        else if (player == 5)
            graphics.setColor(Constants.p5Color);

        //Not implemented
        if (!head) {
            //graphics.drawRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
            graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
        } else {
        } //TODO make headGraphics

        return graphics;
    }
    /**
     * Draws a wall
     *
     * @param p for position, where to draw
     * @param graphics the graphics that will be used
     * @return Updated Graphics
     */
    public static Graphics drawWall(Position p, Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);

        return graphics;
    }

    /**
     * Erases a square, painting it in the same color as background
     *
     * @param p for position, where to draw
     * @param graphics the graphics that will be used
     * @return Updated Graphics
     */
    public static Graphics erase(Position p, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
        return graphics;
    }

    /**
     * Draws a Lightning.
     * @param p for position, where to draw.
     * @param graphics the graphics that will be used.
     * @return Updated Graphics.
     */
    public static Graphics drawLightninh(Position p, Graphics graphics) {
        int[] x = {9,9,7,4,4,6,9};
        int[] y = {0,5,5,9,5,5,0};

        for (int i=0;i<x.length;i++) {
            x[i] +=p.getX()*Constants.gameConstant;
            y[i] +=p.getY()*Constants.gameConstant;
        }
        
        int n = x.length;
        Polygon polygon = new Polygon(x,y,n);

        graphics.setColor(Color.yellow);
        graphics.drawPolygon(polygon);
        graphics.fillPolygon(polygon);

        return graphics;
    }

    /**
     * Draws a Ghost. 
     * @param p for position, where to draw.
     * @param graphics the graphics that will be used.
     * @return Updated Graphics.
     */
    public static Graphics drawGhost(Position p, Graphics graphics) {
        int[] x = {1,9,9,7,3,1};
        int[] y = {9,9,3,1,1,3};

        for (int i=0;i<x.length;i++)
        {
            x[i] +=p.getX()*Constants.gameConstant;
            y[i] +=p.getY()*Constants.gameConstant;
        }

        int n = x.length;
        Polygon polygon = new Polygon(x,y,n);

        graphics.setColor(Color.white);
        graphics.drawPolygon(polygon);
        graphics.setColor(Color.GRAY);
        graphics.fillPolygon(polygon);
        graphics.setColor(Color.ORANGE);
        int k = Constants.gameConstant;
        graphics.fillRoundRect(p.getX()*k,p.getY()*k,4,4,4,4);
        graphics.fillRoundRect(p.getX()*k+5,p.getY()*k,3,3,3,3);

        return graphics;
    }

    /**
     * Draws a Bullet. 
     * @param p for position, where to draw.
     * @param graphics the graphics that will be used.
     * @return Updated Graphics.
     */
	public static Graphics drawBullet(Position p, Graphics graphics) {
		 graphics.setColor(Color.GRAY);
	     graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
	     return graphics;
		
	}
	
    /**
     * Draws a Gun. 
     * @param p for position, where to draw.
     * @param graphics the graphics that will be used.
     * @return Updated Graphics.
     */
	public static Graphics drawGun(Position p, Graphics graphics) {
		 graphics.setColor(Color.LIGHT_GRAY);
	     graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
	     return graphics;	
	}

}
