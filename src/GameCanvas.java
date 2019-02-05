import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements KeyListener{
	
	ArrayList<GameObject> gameObjects;
	Worm worm1;
	public GameCanvas() {
		gameObjects= new ArrayList<GameObject>();
		
		worm1 = new Worm(new Position(0, 100));
		gameObjects.add(worm1);
		gameObjects.add(new Worm(new Position(300, 300)));
		gameObjects.add(new Worm(new Position(30, 500)));
		addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i=0; i< gameObjects.size();i++)
			gameObjects.get(i).draw(g2d);
	}
	public void update() 
	{
		for (int i=0; i < gameObjects.size();i++)
			gameObjects.get(i).update(); System.out.println("hej");	
			
			this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) 
		{
			worm1.direction.y=-1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			worm1.direction.y=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			worm1.direction.x=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			worm1.direction.x=-1;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
