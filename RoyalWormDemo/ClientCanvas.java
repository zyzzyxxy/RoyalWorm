import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionListener;

public class ClientCanvas extends JPanel {

    private char[][] clientWorld;

    public ClientCanvas(char[][] c)
    {
        this.clientWorld = c;
       // this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);


    }

    public void updateClientworld(char[][] world)
    {
        this.clientWorld=world;
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
                if(clientWorld[i][j]!='0')
                    drawObject(clientWorld[i][j], new Position(i,j), g);

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