import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple extends GameObject {

	private final int BODYSIZE = Constants.wormSize;
	
	public Apple() {
		this.type = 'a';
		Random rnd = new Random();
		Position tempPos = new Position(rnd.nextInt(Constants.gameWidth),rnd.nextInt(Constants.gameHeight));
		//Lining it up nicely :)
		tempPos.x=tempPos.x-tempPos.x%Constants.wormSize;
		tempPos.y=tempPos.y-tempPos.y%Constants.wormSize;
		while(CollisionChecker.collisionCheck(tempPos))
		{
			tempPos = new Position(rnd.nextInt(Constants.gameWidth),rnd.nextInt(Constants.gameHeight)); 	
		}
		this.setPos(tempPos);
		CollisionChecker.updateGameworld(tempPos, type);
		
	}
	public void draw(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.fillOval(this.pos.x, this.pos.y, BODYSIZE, BODYSIZE);
		g2d.drawOval(this.pos.x, this.pos.y, BODYSIZE, BODYSIZE);
			
		

	}
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	

}
