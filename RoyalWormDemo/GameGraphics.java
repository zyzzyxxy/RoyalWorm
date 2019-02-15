import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GameGraphics {

    public static Graphics drawApple(Position p, Graphics graphics)
    {
        graphics.setColor(Constants.appleColor);

        graphics.drawOval(p.getX()*Constants.gameConstant,p.getY()*Constants.gameConstant,Constants.gameConstant,Constants.gameConstant);
        graphics.fillOval(p.getX()*Constants.gameConstant,p.getY()*Constants.gameConstant,Constants.gameConstant,Constants.gameConstant);

        return graphics;
    }
    public static Graphics drawPlayer(int player, Position p, Graphics graphics)
    {
        if(player == 1)
            graphics.setColor(Constants.p1Color);
        else if(player == 2)
            graphics.setColor(Constants.p2Color);
        else if(player == 3)
            graphics.setColor(Constants.p3Color);
        else if(player == 4)
            graphics.setColor(Constants.p4Color);

        graphics.drawRect(p.getX()*Constants.gameConstant,p.getY()*Constants.gameConstant,Constants.gameConstant,Constants.gameConstant);
        graphics.fillRect(p.getX()*Constants.gameConstant,p.getY()*Constants.gameConstant,Constants.gameConstant,Constants.gameConstant);

        return graphics;
    }

}
