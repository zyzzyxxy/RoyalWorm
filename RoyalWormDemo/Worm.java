/**
 * Info
 * Params
 * params
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worm extends GameObject {
    private int speed, length, lives;
    private List<Position> body;
    private Position headPos, tailPos, startPos;
    private int wormNumber, counter;
    private char type;
    private Position position;
    private Direction direction, nextDirection;
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

    //Moved to here from GameEngine
    public void updater() throws InterruptedException {
        if(lives > 0) {
            if (counter == speed) {
                update();
                counter = 0;
            } else {
                counter++;
            }
        }
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
        if (CollisionHandler.collisionCheck(headPos))
            CollisionHandler.collisionHandle(this, headPos);
    	body.add(headPos);
    	head = headPos;
    	GameEngine.updateGameworld(headPos, this.type);
        if (body.size() < this.length) {
            tail = null;
        } else {
            GameEngine.updateGameworld(body.get(0), '0');
            tail = tailPos;
            body.remove(0);
        }
        updateHeadPos();
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
        
        Position tmp = generateRandomPos(); 
        headPos = validPos();
    }
    
    public Position generateRandomPos() {
    	Random rn = new Random();
    	int x = rn.nextInt(79);
    	int y = rn.nextInt(59);
    	Position tmp = new Position(x, y);
    	return tmp;
    }
    
    public Position validPos() {
    	Position tmp;
    	do {
    		tmp = generateRandomPos();
    	}while(CollisionHandler.collisionCheck(tmp));
    	return tmp;
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
        int i =0;
        for (Position b:body ) {
            if(p.getX() == b.getX() && p.getY() == b.getY())
                return i;
            i++;
        }

        return -1;
    }

    public void updateHeadPos() {
        headPos = new Position(headPos.getX() + direction.getX(), headPos.getY() + direction.getY());
        if (headPos.getX() >= Constants.worldWidth)
            headPos.setX(0);
        if (headPos.getY() >= Constants.worldHeight)
            headPos.setY(0);
        if (headPos.getX() < 0)
            headPos.setX(Constants.worldWidth - 1);
        if (headPos.getY() < 0)
            headPos.setY(Constants.worldHeight - 1);

    }

    //Just for testing
    public void printBody() {
        for (Position p : body
        ) {

            System.out.println(p.getX());
            System.out.println(p.getY());
        }
    }

	public boolean hasGun() {
		return gun;
	}
	
	public void pickUpGun(){
		gun = true;
	}
	
	public void fireGun() {
		System.out.println("FIRE!!!");
	}

	public Direction getDirection() {
		return direction;
	}
	
	public Direction getNextDirection() {
		return nextDirection;
	} 
	
	public int getLives() {
		return lives;
	}
	public int getSpeed() {
		return speed;
	}
	public int getCounter() {
		return counter;
	}

}



