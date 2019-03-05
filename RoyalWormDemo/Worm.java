import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Worm extends GameObject {
    private int speed, length, lives;
    private List<Position> body;
    private Position headPos, tailPos, startPos;
    private int wormNumber, counter;
    private char objChar;
    private Position pos;
    private Direction dir;

    public Worm(Position pos, Direction dir, int wormNumber) {
        super(pos, (char) wormNumber);
        this.pos = pos;
        this.dir = dir;
        this.wormNumber = wormNumber;
        speed = Constants.wormspeed;
        length = Constants.wormStartingLength;
        headPos = pos;
        tailPos = pos;
        startPos = pos;
        lives = 3;
        counter = 0;
        objChar = (char) wormNumber;
        body = new ArrayList<>();
    }

    public void update() throws InterruptedException {
        updateBody();
    }

    public Position getHeadPos() {
        return headPos;
    }

    public Position getTailPos() {
        return tailPos;
    }

    //Doesnt work must update instancieted GE
    public void updateBody() throws InterruptedException {
        Position head, tail;
        head = null;
        tail = null;
        
        boolean collision = false;
        
        if (CollisionHandler.collisionCheck(headPos)) {
            collision = CollisionHandler.collisionHandle(this, headPos);
        }
        if (!collision) {
        body.add(headPos);
        head = headPos;
        //  tailPos=body.get(0);
        if (body.size() < length) {
            tail = null;
            Main.gameController.gameEngine.updateGameWorld(headPos, this);
        } else {
        	Main.gameController.gameEngine.updateGameWorld(headPos, this);
        	Main.gameController.gameEngine.updateGameWorld(body.get(0), new EmptyObject());
            tail = tailPos;
            body.remove(0);
        }
        updateHeadPos();
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
        System.out.println("Worm " + objChar +" have " + lives + " lives");
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public void reset() {
        //Todo fix this
        for (Position p : body) {
            GameEngine.updateGameworld(p, new EmptyObject());
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

    public void updateHeadPos() {
        headPos = new Position(headPos.x + direction.x, headPos.y + direction.getY());
        if (headPos.x >= Constants.worldWidth)
            headPos.x = 0;
        if (headPos.y >= Constants.worldHeight)
            headPos.y = 0;
        if (headPos.x < 0)
            headPos.x = Constants.worldWidth - 1;
        if (headPos.y < 0)
            headPos.y = Constants.worldHeight - 1;
    }

    public void stop() {
    	speed = 0;
    }

    //Just for testing

    public void printBody() {
        for (Position p : body
        ) {

            System.out.println(p.x);
            System.out.println(p.y);
        }
    }
    
    public int getSpeed() {
    	return speed;
    }

    public Direction getDirection() {
    	return dir;
    }

}



