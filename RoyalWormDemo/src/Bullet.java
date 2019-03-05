import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Bullet extends GameObject {
    int speed, length, lives;
    List<Position> body;
  //  List<Bullet> bullets;
    Position headPos, tailPos, startPos;
    //Position[] updatedPos = new Position[2];
    int wormNumber, counter;
    char type;
    BoardCordinates position;
    private BoardCordinates direction;
    boolean gun;
    int dirx, diry;
	private boolean continueProjection;
    Worm w;

    public Bullet(Position position, BoardCordinates direction, Worm w) {
        super(position, 'b');
        this.position = position;
        this.direction = direction;
        this.speed = Constants.wormspeed;
        this.length = 1;
        
        this.headPos = position;
        this.tailPos = position;
        this.counter = 0;
        this.type = 'b';
        dirx = direction.getX();
        diry = direction.getY();
        body = new ArrayList<>();
        continueProjection = true;
        this.w = w;
    }



    public void update() throws InterruptedException {
        updatePos();
    }

    public Position getHeadPos() {
        return headPos;
    }

    public Position getTailPos() {
        return tailPos;
    }

    //Doesnt work must update instancieted GE
    public void updatePos() throws InterruptedException {
    	if(continueProjection) {
        Position head, tail;
        head = null;
        tail = null;
        if (body.size() < this.length) {
            if (CollisionHandler.collisionCheck(headPos))
                CollisionHandler.collisionHandle(this, headPos);
            body.add(headPos);
            head = headPos;
            //  tailPos=body.get(0);
            tail = null;
            GameEngine.updateGameworld(headPos, this.type);
            updatePos2();
        } else {
            if (CollisionHandler.collisionCheck(headPos))
                CollisionHandler.collisionHandle(this, headPos);

            body.add(headPos);
            head = headPos;
            GameEngine.updateGameworld(headPos, this.type);
            GameEngine.updateGameworld(body.get(0), '0');
            tail = tailPos;
            body.remove(0);
            //  tailPos = body.get(0);
            updatePos2();

        }
    }
    }
    public void grow() {
        length += 1;
    }

    public void grow(int n) {
        length += n;
    }

    public void shrink() {
        length -= 1;
    }

    public void shrink(int n) {
        length -= n;
    }

    public void looseLife() {
        lives--;
        System.out.println("Worm " + type +" have " + lives + " lives");
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public void reset() {
        //Todo fix this
        for (Position p : body) {
            GameEngine.updateGameworld(p, '0');
        }
        body.clear();
        length = Constants.wormStartingLength;
        headPos = startPos;
    }

    public void addToSpeed(int n) {
        speed -= n;
    }
    public void resetSpeed() {
        speed = Constants.wormspeed;
    }

    public void updatePos2() {
    	
    	if(continueProjection) {
    		//System.out.println(continueProjection);
        headPos = new Position(headPos.x + dirx, headPos.y + diry);
        if (headPos.x >= Constants.worldWidth)
            headPos.x = 0;
        if (headPos.y >= Constants.worldHeight)
            headPos.y = 0;
        if (headPos.x < 0)
            headPos.x = Constants.worldWidth - 1;
        if (headPos.y < 0)
            headPos.y = Constants.worldHeight - 1;
        
    	}
    }

    //Just for testing

    public void printBody() {
        for (Position p : body
        ) {
        	
            System.out.println(p.x);
            System.out.println(p.y);
        }
    }
    
    public void setProjection() {
    	continueProjection = false;
    }
    
    public boolean getProjection() {
    	return continueProjection;
    }
    
   public Worm getWorm() {
	   return w;
   }





}



