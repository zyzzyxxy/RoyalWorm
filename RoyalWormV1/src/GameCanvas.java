import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements KeyListener{
	
	ArrayList<GameObject> gameObjects;
	Worm worm1;
	Worm worm2;

	public GameCanvas() {
		gameObjects= new ArrayList<GameObject>();
		
		worm1 = new Worm(new Position(0, 100), Color.GREEN); gameObjects.add(worm1);
		worm2 = new Worm(new Position(700, 300), Color.CYAN); gameObjects.add(worm2);

		gameObjects.add(new Worm(new Position(300, 300),Color.red));
		gameObjects.add(new Worm(new Position(30, 500),Color.BLUE));
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
			gameObjects.get(i).update();
			
			this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP && worm1.direction.y!=1) {worm1.direction.y=-1;	worm1.direction.x=0;}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN && worm1.direction.y!=-1){worm1.direction.y=1;worm1.direction.x=0;}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT && worm1.direction.x!=-1){worm1.direction.y=0;worm1.direction.x=1;}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT&& worm1.direction.x!=1){worm1.direction.y=0;worm1.direction.x=-1;}
		
		else if(e.getKeyCode()==KeyEvent.VK_W && worm2.direction.y!=1) {worm2.direction.y=-1;worm2.direction.x=0;}
		else if(e.getKeyCode()==KeyEvent.VK_S && worm2.direction.y!=-1){worm2.direction.y=1;worm2.direction.x=0;}
		else if(e.getKeyCode()==KeyEvent.VK_D && worm2.direction.x!=-1){worm2.direction.y=0;worm2.direction.x=1;}
		else if(e.getKeyCode()==KeyEvent.VK_A&& worm2.direction.x!=1){worm2.direction.y=0;worm2.direction.x=-1;}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void spawn(String str){
		try {
			switch (str) 
			{
			case "apple":
				GameObject apple = new Apple();
				gameObjects.add(apple);
				CollisionChecker.gameWorld[apple.getPos().x/Constants.wormSize][apple.getPos().y/Constants.wormSize]=apple.type;
			}
			
			
		}
		catch(IllegalArgumentException e){System.out.println("Bad stringArg");}
		
	}
}
