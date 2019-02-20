import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Worm extends Observable/*extends DynamicObject*/implements Runnable{
    int speed, length;
    List<Position> body;
    Position headPos,tailPos, startPos;
    Position[] updatedPos = new Position[2];
    int wormNumber;
    char type;
    BoardCordinates position,direction;
    
    boolean running;


    public Worm(Position position, Direction direction, int wormNumber) {
        this.position = position;
        this.direction = direction;
        this.speed = Constants.wormspeed;
        this.length = Constants.wormStartingLength;
        this.wormNumber = wormNumber;
        this.headPos = position;
        this.tailPos = position;
        this.startPos = position;
        this.type= Integer.toString(wormNumber).charAt(0);
        body = new ArrayList<>();
        
        running = true;
    }

    //@Override
     public void run() {
        while (running) {
            update();
            try {
            	Thread.sleep(1000 / speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void update()
    {
        setChanged();
        notifyObservers(updateBody());
    }
    public Position getHeadPos(){return headPos;}
    public Position getTailPos(){return tailPos;}
    //Doesnt work must update instancieted GE
    public Position[] updateBody()
    {
        Position head,tail;
        //System.out.printf("head: %s" , headPos.getX());
        if(GameEngine.CH.collisionCheck(headPos)) {
	           GameEngine.CH.collisionHandle(this,headPos);
	           System.out.printf("headpos: %d", headPos.getY());
         }
        body.add(headPos);
        head=headPos;
        tailPos=body.get(0);
	        if(body.size()<this.length)
	        {
	            tail = null;
		           //GameEngine.updateGameworld(headPos, this.type);
	        }
	        else
	        {
	         //   GameEngine.updateGameworld(headPos, this.type);
	         //   GameEngine.updateGameworld(body.get(0), '0');
	           tail=tailPos;
	           body.remove(0);
	        }
	    updateHeadPos();
        return new Position[]{head,tail};
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
    public void stop()
    {
    	System.out.print("STOP");
    	//GameEngine.printGameWorld();
    	running = false;
    }
    public void reset()
    {
        //Todo fix this
        body.clear();
        length=Constants.wormStartingLength;
        headPos=startPos;
    }
    public void addToSpeed(int n)
    {
        speed+=n;
    }
    public void updateHeadPos()
    {
        headPos = new Position(headPos.x+direction.x, headPos.y+direction.getY());
        if(headPos.x>=Constants.worldWidth)
            headPos.x=0;
        if(headPos.y>=Constants.worldHeight)
            headPos.y=0;
        if(headPos.x<0)
            headPos.x=Constants.worldWidth-1;
        if(headPos.y<0)
            headPos.y=Constants.worldHeight-1;

    }
    //Just for testing
    public void printBody()
    {
        for (Position p:body) {
            System.out.println(p.x);
            System.out.println(p.y);
        }
    }
}



