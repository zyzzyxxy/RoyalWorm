import java.awt.Graphics;

public abstract class GameObject {
	
	Position pos;
	
	public Position getPos()
	{
		return pos;
	}
	
	public void setPos(Position pos)
	{
		this.pos=pos;
	}
	
	public void draw(Graphics g) 
	{
		
	}

	protected abstract void update();
}
