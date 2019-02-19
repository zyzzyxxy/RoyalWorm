import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class GameCanvas extends JPanel {


    public GameCanvas()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);
        this.repaint();
        setBackground(Constants.backgroundColor);
    }

    @Override
    public void paint(Graphics g) {
        drawWorld(g);
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
    public void drawObject(char c, Position p, Graphics g)
    {
        switch (c)
        {
            case 'a':
                GameGraphics.drawApple(p, g);
                break;
                //For players
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
        }
    }

}
