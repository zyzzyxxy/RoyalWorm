import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple extends GameObject {

	private final int BODYSIZE = 15;
	
	public Apple() {
		Random rnd = new Random();
		Position tempPos = new Position(rnd.nextInt(800),rnd.nextInt(600)); //TODO get rid of ghostnumbers
		this.setPos(tempPos);
		
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
