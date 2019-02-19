import java.awt.*;

public class GameGraphics {

    public static Graphics drawApple(Position p, Graphics graphics)
    {
        int constant = Constants.gameConstant;
        graphics.setColor(Constants.appleColor);

        graphics.drawOval(p.getX()*constant,p.getY()*constant,constant,constant);
        graphics.fillOval(p.getX()*constant,p.getY()*constant,constant,constant);
        graphics.setColor(Color.GREEN);
        //Todo make details better :)
        //Draw details, just for fun
        for(int i = 0; i<5;i++) {
            graphics.drawArc(p.getX() * constant - constant + constant / 2, p.getY() * constant-i, 10+i, 10, 0, 90);
        }
        return graphics;
    }
    public static Graphics drawSuperApple(Position p, Graphics graphics)
    {
        int constant = Constants.gameConstant;
        graphics.setColor(Color.magenta);

        graphics.drawOval(p.getX()*constant,p.getY()*constant,constant,constant);
        graphics.fillOval(p.getX()*constant,p.getY()*constant,constant,constant);
        graphics.setColor(Color.orange);
        //Todo make details better :)
        //Draw details, just for fun
        for(int i = 0; i<5;i++) {
            graphics.drawArc(p.getX() * constant - constant + constant / 2, p.getY() * constant-i, 10+i, 10, 0, 90);
        }
        return graphics;
    }


    public static Graphics drawPlayer(boolean head, int player, Position p, Graphics graphics)
    {
        if(player == 1)
            graphics.setColor(Constants.p1Color);
        else if(player == 2)
            graphics.setColor(Constants.p2Color);
        else if(player == 3)
            graphics.setColor(Constants.p3Color);
        else if(player == 4)
            graphics.setColor(Constants.p4Color);

        if(!head) {
            //graphics.drawRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
            graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);
        }
        else{} //Todo make headGraphics

        return graphics;
    }
    public static Graphics drawWall(Position p, Graphics graphics)
    {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);

        return graphics;
    }
    public static Graphics erase(Position p, Graphics graphics)
    {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(p.getX() * Constants.gameConstant, p.getY() * Constants.gameConstant, Constants.gameConstant, Constants.gameConstant);

        return graphics;
    }

}
