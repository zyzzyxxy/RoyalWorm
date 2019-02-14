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

}
