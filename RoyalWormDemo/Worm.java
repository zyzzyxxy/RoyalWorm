import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Worm extends GameObject {
    public int getSpeed() {
        return speed;
    }

    private int speed;
    private int length;

    public int getLives() {
        return lives;
    }

    private int lives;
    private List<Position> body;
    private Position headPos, tailPos, startPos;
    private int wormNumber;

    public int getCounter() {
        return counter;
    }

    private int counter;
    private char type;
    private BoardCordinates position;

    private BoardCordinates direction;

    public BoardCordinates getDirection() {
        return direction;
    }


    private BoardCordinates nextDirection;

    public void setNextDirection(BoardCordinates nextDirection) {
        this.nextDirection = nextDirection;
    }

    private int isFast = 0;
    private boolean gun;


    public Worm(Position position, Direction direction, int wormNumber) {
        super(position, Integer.toString(wormNumber).charAt(0));
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
        this.speed = Constants.wormspeed;
        this.length = Constants.wormStartingLength;
        this.wormNumber = wormNumber;
        this.headPos = position;
        this.tailPos = position;
        this.startPos = position;
        this.lives = 3;
        this.counter = 0;
        gun = false;
        this.type = Integer.toString(wormNumber).charAt(0);
        body = new ArrayList<>();
    }


    public void update() throws InterruptedException {
        direction = nextDirection;
        updateBody();
    }
    public void resetCounter() { counter=0; }
    public void incWormCounter() { counter++; }

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

    public void looseLife() {
        lives--;
        System.out.println("Worm " + type + " have " + lives + " lives");
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

    public void lightninhMode() {
        isFast++;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Running thread in CH");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (--isFast == 0) {
                    resetSpeed();
                }
            }
        });
        t.start();
        speed = (int) (Constants.wormspeed / 1.5);
    }

    public void resetSpeed() {
        speed = Constants.wormspeed;
    }

    public void cut(Position p) {
        int i = isInBody(p);
        if (i != -1) {

            //body = body.subList(0,i);

            try {
                List<Position> bodyRests = new ArrayList<Position>(body.subList(0, i));
                for (Position k : bodyRests) {
                    GameEngine.updateGameworld(k, '0');
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            body = new ArrayList<Position>(body.subList(i, body.size()));
            length = body.size();
        }
    }

    public int isInBody(Position p) {
        int i = 0;
        for (Position b : body) {
            if (p.x == b.x && p.y == b.y)
                return i;
            i++;
        }

        return -1;
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


    public boolean hasGun() {

        return gun;
    }

    public void pickUpGun() {
        gun = true;
    }

    public void fireGun() {
        System.out.println("FIRE!!!");
    }


}



