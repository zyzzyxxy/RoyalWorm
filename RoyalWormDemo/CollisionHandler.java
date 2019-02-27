public class CollisionHandler {
    private static int squareWidth = (Constants.boardWidth/Constants.gameConstant) - 2;
    private static int squareHeight = (Constants.boardHeight/Constants.gameConstant) - 2;
	
	
	public CollisionHandler() {

	}
	
	public static boolean collisionCheck(Position pos) {
		if (Constants.WallCollision && (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight)) {
			return true;
		}
		return GameEngine.GameWorld[pos.getX()][pos.getY()] != '0';
	}

	public static boolean collisionHandle(Worm worm, Position pos) throws InterruptedException {
		if (Constants.WallCollision && (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight)) {
			wormToWorm(worm);
			return true;
		} else {
			char posValue = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if (posValue == 'W' || posValue == '1' || posValue == '2') {
				wormToWorm(worm);
				return true;
			}
			
			if (posValue == 'a') {
				wormToApple(pos, worm);
			}

			if (posValue == 's') {
				wormToS(pos, worm);
			}
			
			if (posValue == 'l') {
				worm.addToSpeed(Constants.wormspeed/2);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {

						System.out.println("Running thread in CH");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						worm.resetSpeed();

					}
				});
				t.start();
				if(!t.isAlive()){
					t.join();
					System.out.println("Joined");
				}
			}
		}
		return false;
	}
	
	private static void wormToWorm(Worm worm) {
		worm.stop();
		worm.looseLife();
	}
	
	private static void wormToApple(Position pos, Worm worm) {
		worm.grow();
		//GameEngine.boostManager.delete(pos);
	}

	private static void wormToS(Position pos, Worm worm) {
		worm.grow(10);
		//GameEngine.boostManager.delete(pos);
	}
}
