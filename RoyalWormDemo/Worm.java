import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Worm extends GameObject {
    int speed, length, lives;
    List<Position> body;
    Position headPos, tailPos, startPos;
    //Position[] updatedPos = new Position[2];
    int wormNumber, counter;
    char type;
    BoardCordinates position, direction;


    public Worm(Position position, Direction direction, int wormNumber) {
        super(position, Integer.toString(wormNumber).charAt(0));
        this.position = position;
        this.direction = direction;
        this.speed = Constants.wormspeed;
        this.length = Constants.wormStartingLength;
        this.wormNumber = wormNumber;
        this.headPos = position;
        this.tailPos = position;
        this.startPos = position;
        this.lives = 3;
        this.counter = 0;
        this.type = Integer.toString(wormNumber).charAt(0);
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
        if (body.size() < this.length) {
            if (CollisionHandler.collisionCheck(headPos))
                CollisionHandler.collisionHandle(this, headPos);
            body.add(headPos);
            head = headPos;
            //  tailPos=body.get(0);
            tail = null;
            GameEngine.updateGameworld(headPos, this.type);
            updateHeadPos();
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

    //Just for testing

    public void printBody() {
        for (Position p : body
        ) {

            System.out.println(p.x);
            System.out.println(p.y);
        }
    }


}



