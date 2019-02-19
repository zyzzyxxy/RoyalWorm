import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCanvas extends JPanel {

    public Direction direction;

    public GameCanvas()
    {
        direction = new Direction(-1,0);
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        //drawWorld(g);
        drawChanges(g);
    }

    public void drawWorld(Graphics g)
    {
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
                if(GameEngine.GameWorld[i][j]!='0')
                    drawObject(GameEngine.GameWorld[i][j], new Position(i,j), g);

            }
    }
    public void drawChanges(Graphics g)
    {
        for (Change ch:GameEngine.changes) {
            drawObject(ch.type, new Position(ch.x,ch.y), g);
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
            case 'w':
                GameGraphics.drawWall(p,g);
                break;
            case 's':
                GameGraphics.drawSuperApple(p,g);
                break;
        }
    }
}
