import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GameCanvas extends JPanel {


    public GameCanvas()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);


        //testPaint();
        //drawWorld();
        this.repaint();

    }

    @Override
    public void paint(Graphics g) {

        //g.setColor(Color.blue);
        //g.fillOval(50,50,50,50);
        drawWorld(g);
    }

    public void testPaint()
    {

    }
    public void drawWorld(Graphics g)
    {
        for (int i = 0;i<Constants.worldHeight;i++)
            for (int j = 0;j<Constants.worldWidth;j++)
            {
                if(GameEngine.GameWorld[i][j]!='0')
                    drawObject(GameEngine.GameWorld[i][j], new Position(j,i), g);

            }
    }
    public void drawObject(char c, Position p, Graphics g)
    {
        switch (c)
        {
            case 'a':
                GameGraphics.drawApple(p, g);
                break;
                //For players
            case '1':
                GameGraphics.drawPlayer(1,p,g);
                break;
            case '2':
                GameGraphics.drawPlayer(2,p,g);
                break;
            case '3':
                GameGraphics.drawPlayer(3,p,g);
                break;
            case '4':
                GameGraphics.drawPlayer(4,p,g);
                break;
        }
    }

}
