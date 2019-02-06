import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Worm extends GameObject{

	private int length, speed,wormSize;
	private final int STARTLENGTH = 8;
	Position startPos;
	Direction startDirection;
	List<Position> body; 
	//Wich way we are giong
	//TODO fix better direction!
	public Direction direction;
	Graphics2D wormGraphics;
	Position headPos;
	Color color;
	
	
	public Worm(Position pos, Color color) 
	{
		//lining it upp
		pos.x-=pos.x%Constants.wormSize;
		pos.y-=pos.y%Constants.wormSize;
		this.headPos = pos;
		this.startPos = pos;
		this.length = STARTLENGTH;
		this.speed = 3;
		this.direction= new Direction(1, 0);
		this.startDirection=direction;
		this.wormSize = Constants.wormSize;
		this.color = color;
		this.type = 'w';
		
		body = new LinkedList<Position>();
		body.add(pos);
		
		
		update();


	}

	public void draw(Graphics g) 
	{
		int x,y;
		Graphics2D g2d = (Graphics2D) g;

		for(int i=0; i<body.size();i++) 
		{
			x=body.get(i).x; y=body.get(i).y;
			g2d.setColor(color);
			g2d.fillRect(x, y, wormSize, wormSize);
			g2d.drawRect(x, y, wormSize, wormSize);
		}

	}

	public void update() 
	{
		updateBody();
	}
	private void updateBody() 
	{
		if(body.size()<this.length) 
		{
			if(CollisionChecker.collisionCheck(headPos))
				CollisionChecker.collisionHandle(this,headPos);
			body.add(headPos);
			headPos = new Position(headPos.x+direction.getX()*wormSize, headPos.y+direction.getY()*wormSize);	
		}
		else
		{
			body.add(headPos);
			body.remove(0);
			headPos = new Position(headPos.x+direction.getX()*wormSize, headPos.y+direction.getY()*wormSize);
		}
	}
	
	public Graphics2D getGraphics2d() 
	{
		return wormGraphics;
	}
	public void grow() 
	{
		length+=1; 
	}
	public void grow(int n) 
	{
		length+=n; 
	}
	public void shrink() 
	{
		length-=1; 
	}
	public void shrink(int n) 
	{
		length-=n; 
	}
	public void reset() 
	{
		body.clear();
		length=STARTLENGTH; 
		headPos=startPos;
	}
}
