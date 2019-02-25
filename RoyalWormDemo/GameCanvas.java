import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;

public class GameCanvas extends JPanel {

    //public Direction direction;
    public List<Change> changes;

    public GameCanvas()
    {
        changes = GameEngine.changes;
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        this.repaint();
       /* Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                drawChanges(getParent().getGraphics());
            }
        });
        t.start();*/
    }

    @Override
    public void paint(Graphics g) {
        drawWorld(g);
        //g = this.getGraphics();
        //
        //drawChanges(g);
    }

    public void drawWorld(Graphics g)
    {
        //Brute Force
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
                //if(GameEngine.GameWorld[i][j]!='0')
                    drawObject(GameEngine.GameWorld[i][j], new Position(i,j), g);

            }
        //int i, j;
        /*for(Change ch: changes)
        {
            i=ch.x;j=ch.y;
            drawObject(GameEngine.GameWorld[i][j], new Position(i,j), g);
        }*/
    }
    public void drawChanges(Graphics g)
    {
        if(!changes.isEmpty())
        {
            for (Change ch :changes) {
                drawObject(ch.type,new Position(ch.x,ch.y),g);
            }

        }
        GameEngine.clearChanges();
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
            case 'l':
                GameGraphics.drawSlow(p,g);
                break;
        }
    }
}