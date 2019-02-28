import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class BulletV1 extends GameObject {
    int speed;
   // List<Position> body;
    Position pos;
    //Position[] updatedPos = new Position[2];
    int counter;
    char type;
    Direction dir;
   BoardCordinates position, direction;
    boolean gun;


    public BulletV1(Position position, Worm w) {
        
    	super(position, 'b');
    	this.position = position;
    	this.position.x = this.position.x;
    	this.position.y = this.position.y;
    	this.dir = (Direction) w.direction;
    	this.speed = Constants.wormspeed+1;
    	this.pos = position;
    	//super(new Position(w.headPos.x+1, w.headPos.y), 'b');
        //this.pos = new Position(w.headPos.x+1, w.headPos.y);   
       // this.direction = w.direction;
       // this.pos = pos;
        //super(position, 'b');
 
    
        this.counter = 0;

       
    }







    public Position getPos() {
        return pos;
    }

    //Doesnt work must update instancieted GE
    public void updateBody() throws InterruptedException {
        //Position head, tail;
        //head = null;
        //tail = null;
       // if (body.size() < this.length) {
           // if (CollisionHandler.collisionCheck(pos))
              //  CollisionHandler.collisionHandle(this, pos);
           // body.add(headPos);
           // head = headPos;
            //  tailPos=body.get(0);
           // tail = null;
            GameEngine.updateGameworld(pos, this.type);
           // updateHeadPos();
       // } else {
         //   if (CollisionHandler.collisionCheck(headPos))
           //     CollisionHandler.collisionHandle(this, headPos);

            //body.add(headPos);
            //head = headPos;
            //GameEngine.updateGameworld(headPos, this.type);
          //  GameEngine.updateGameworld(body.get(0), '0');
           // tail = tailPos;
           // body.remove(0);
            //  tailPos = body.get(0);
            updatePos();

        }
    

    public void resetSpeed() {
        speed = Constants.wormspeed;
    }

    public void updatePos() {
    	GameEngine.updateGameworld(pos, 'b');
    	pos = new Position(pos.x + dir.x, pos.y + dir.getY());
        if (pos.x >= Constants.worldWidth)
            pos.x = 0;
        if (pos.y >= Constants.worldHeight)
            pos.y = 0;
        if (pos.x < 0)
            pos.x = Constants.worldWidth - 1;
        if (pos.y < 0)
            pos.y = Constants.worldHeight - 1;

    }


}



