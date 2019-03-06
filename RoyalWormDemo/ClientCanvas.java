import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.Arrays;

public class ClientCanvas extends JPanel {

    private char[][] clientWorld;
    private int paintCounter = 0;

    public ClientCanvas(char[][] c)
    {
    	clientWorld = new char[Constants.worldWidth][Constants.worldHeight];
        for (char[] c1 : clientWorld) {
            Arrays.fill(c1, '0');
        }
        
        updateClientWorld(c);
        setSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);
    }

    public void updateClientWorld(char[][] world)
    {
    	for (int y = 0; y < Constants.worldHeight; y++) {
    		for (int x = 0; x < Constants.worldWidth; x++) {
    			clientWorld[x][y] = world[x][y];
    		}		
    	}
    }

    public char getClientWorld(int x, int y) {
    	return clientWorld[x][y];
    }
    
    @Override
    public void paint(Graphics g) {
        if (paintCounter < 3) {
            drawWorld(g);
            paintCounter++;
        } else {
            //drawChanges(g);
            drawWorld(g);
        }
    }

    public void drawChanges(Graphics g)
    {
        if(!ClientWindow.changes.isEmpty())
        {
            for (Change ch : ClientWindow.changes) {
                drawObject(ch.getType(),new Position(ch.getX(),ch.getY()),g);
            }
        }
    }
    
    //TODO somethings on right not paining
    public void drawWorld(Graphics g)
    {
        for (int i = 0;i<Constants.worldWidth;i++)
            for (int j = 0;j<Constants.worldHeight;j++)
            {
                //if(clientWorld[i][j]!='0')
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
            case '5':
                GameGraphics.drawPlayer(false,5,p,g);
                break;
            case 'w':
                GameGraphics.drawWall(p,g);
                break;
            case 's':
                GameGraphics.drawSuperApple(p,g);
                break;
            case 'l':
                GameGraphics.drawLightninh(p,g);
                break;
            case 'g':
                GameGraphics.drawGhost(p,g);
                break;
        }
    }
}
