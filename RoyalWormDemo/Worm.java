/**
 * @author Johan Ericsson, Anton Eliasson Gustavsson, Jonathan Uhre
 * @Version 2019-03-09
 *
 * A worm class. This is the worm that will be present on the gameboard. It contains info about
 * the lives, speed and score of the worm.
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worm extends GameObject {
    private int speed, length, lives, score;
    private List<Position> body;
    private List<Bullet> bullets;
    private Position headPos, tailPos, startPos;
    private int wormNumber, counter;
    private char type;
    private Position position;
    private Direction direction, nextDirection;
    private int isFast = 0;
    private boolean gun;
    private boolean fireAllowed;


    /**
     * Constructor
     *
     * @param position      the startposition of the worm
     * @param direction     the direction that the worm will be starting with
     * @param wormNumber    this number indicates what worm and what player is assigned to that worm.
     *                      For instance host will have worm 1 and it will be blue.
     */
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
        fireAllowed = true;
        this.type = Integer.toString(wormNumber).charAt(0);
        body = new ArrayList<>();
        bullets = new ArrayList<>();
    }


    public void update() throws InterruptedException {
        direction = nextDirection;
        updateBody();
    }
    public void resetCounter() { counter=0; }
    public void incWormCounter() { counter++; }

    /**
     * Checks if time to update
     *
     * @throws InterruptedException
     */
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

    /**
     * Updates worms body. Adding a new head in direction and taking away a bit in the tail
     *
     * @throws InterruptedException
     */
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

    public void grow(int n) {
        length += n;
        score += n;
    }

    public void loseLife() {
        lives--;
        System.out.println("Worm " + type + " have " + lives + " lives");
    }

    public boolean isAlive() {
        return lives > 0;
    }

    /**
     * resets the worm
     */
    public void reset() {
        //Todo fix this
        for (Position p : body) {
            GameEngine.updateGameworld(p, '0');
        }
        body.clear();
        length = Constants.wormStartingLength;
        
        Position tmp = generateRandomPos(); 
        headPos = validPos();
        
        loseLife();
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

    /**
     * Makes the worm fast for 5 seconds
     */
    public void lightningMode() {
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
        score += 1;
    }

    public void resetSpeed() {
        speed = Constants.wormspeed;
    }

    /**
     * Cuts the worm keeping the part with head
     *
     * @param p the position to cut the worm
     */
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

    /**
     * Checks if a position is in the worms body
     *
     * @param p the position to be checked
     * @return the index of the position if it is in body, else -1.
     */
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

	public boolean hasGun() {
		return gun;
	}
	
	public void pickUpGun(){
		gun = true;
		score += 3;
	}

    /**
     * Fires the gun, shooting a bullet in the direction of the worm
     */
	public void fireGun() {
		if (gun && fireAllowed) {
			System.out.println("FIRE!!!");
			int x = headPos.getX()+1;
			int y = headPos.getY() +1;
			Position tmp = new Position(x, y);
			Bullet b = new Bullet(tmp, this);
			bullets.add(b);
			fireAllowed = false;
		}
	}
	
	public void removeBullet(Bullet b) {
		bullets.remove(b);
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
	public int getScore() {
		return score;
	}
	public List<Bullet> getBullets(){
		return bullets;
	}
	public void setScore(int s) {
		score = s;
	}
	
	public boolean toggleFireAllowed() {
		fireAllowed = true;
		return fireAllowed;
	}

}


