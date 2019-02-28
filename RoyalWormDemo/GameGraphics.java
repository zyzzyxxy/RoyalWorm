/**
 * This Class takes care of the graphics. May be modified for nicer graphics.
 */

import java.awt.*;

public class GameGraphics {

    /**
     * Draws an apple.
     *
     * @return Updated Graphics
     * @param: Position and Graphics. The position to where to draw and the graphics that will be used
     */
    public static Graphics drawApple(Position p, Graphics graphics) {
        int constant = Constants.gameConstant;
        graphics.setColor(Constants.appleColor);

        graphics.drawOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.fillOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.setColor(Color.GREEN);
        //Todo make details better :)
        //Draw details, just for fun

        for (int i = 0; i < 5; i++) {
            graphics.drawArc(p.getX() * constant - constant + constant / 2, p.getY() * constant - i, 10 + i, 10, 0, 90);
        }
        return graphics;
    }

    public static Graphics drawSuperApple(Position p, Graphics graphics) {
        int constant = Constants.gameConstant;
        graphics.setColor(Color.magenta);

        graphics.drawOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.fillOval(p.getX() * constant, p.getY() * constant, constant, constant);
        graphics.setColor(Color.orange);
        //Todo make details better :)
        //Draw details, just for fun

        for (int i = 0; i < 5; i++) {
            graphics.drawArc(p.getX() * constant - constant + constant / 2, p.getY() * constant - i, 10 + i, 10, 0, 90);
        }
        return graphics;
    }


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

        if (!head) {
            //graphics.drawRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
            graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
        } else {
        } //Todo make headGraphics

        return graphics;
    }

    public static Graphics drawWall(Position p, Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);

        return graphics;
    }

    public static Graphics erase(Position p, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);

        return graphics;
    }

    public static Graphics drawLightninh(Position p, Graphics graphics) {
        int[] x = {9,9,7,4,4,6,9};
        int[] y = {0,5,5,9,5,5,0};

        for (int i=0;i<x.length;i++)
        {
            x[i] +=p.x*Constants.gameConstant;
            y[i] +=p.y*Constants.gameConstant;

        }


        int n = x.length;
        Polygon polygon = new Polygon(x,y,n);

        graphics.setColor(Color.yellow);
        graphics.drawPolygon(polygon);
        graphics.fillPolygon(polygon);

        return graphics;
    }
    public static Graphics drawGhost(Position p, Graphics graphics) {
        int[] x = {1,9,9,7,3,1};
        int[] y = {9,9,3,1,1,3};

        for (int i=0;i<x.length;i++)
        {
            x[i] +=p.x*Constants.gameConstant;
            y[i] +=p.y*Constants.gameConstant;

        }


        int n = x.length;
        Polygon polygon = new Polygon(x,y,n);

        graphics.setColor(Color.white);
        graphics.drawPolygon(polygon);
        graphics.setColor(Color.GRAY);
        graphics.fillPolygon(polygon);
        graphics.setColor(Color.ORANGE);
        int k = Constants.gameConstant;
        graphics.fillRoundRect(p.x*k,p.y*k,4,4,4,4);
        graphics.fillRoundRect(p.x*k+5,p.y*k,3,3,3,3);

        return graphics;
    }

}
