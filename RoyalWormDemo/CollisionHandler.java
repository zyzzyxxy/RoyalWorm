import java.util.List;

public class CollisionHandler {
    private static int squareWidth = (Constants.boardWidth/Constants.gameConstant) - 2;
    private static int squareHeight = (Constants.boardHeight/Constants.gameConstant) - 2;
	
	
	public CollisionHandler() {

	}
	
	public static boolean collisionCheck(Position pos) {
		if (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight) {
			return Constants.WallCollision;
		}
		return Main.gameController.gameEngine.getFromGameWorld(pos.getX(), pos.getY()) instanceof EmptyObject;
	}

	public static boolean collisionHandle(Worm worm, Position pos) throws InterruptedException {
		if (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight) {
			if (Constants.WallCollision) {
				crashCollision(worm);
				return true;
			} else {
				return false;
			}
		} else {
			char posValue = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if (posValue == 'W' || posValue == '1' || posValue == '2') {
				crashCollision(worm);
				return true;
			}
			
			if (posValue == 'a') {
				appleCollision(pos, worm);
			}

			if (posValue == 's') {
				superAppleCollision(pos, worm);
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
	private static void resetCheck() {
		List<Player> playerList = GameEngine.getPlayerList();
		
		
		if ((playerList.size() == 1) && (playerList.get(0).getWorm().getSpeed() == 0)) {
			//TODO reset
			System.out.print("Reset");
		} else {
			int counter = 0;
			for (Player p : playerList) {
				if (p.getWorm().getSpeed() == 0) {
					counter++;
				}
			}
			if (counter == playerList.size() - 1) {}
			//TODO reset
			System.out.print("Reset");
		}
	}
	
	private static void crashCollision(Worm worm) {
		worm.stop();
		worm.looseLife();
	}
	
	private static void appleCollision(Position pos, Worm worm) {
		worm.grow();
		//GameEngine.boostManager.delete(pos);
	}

	private static void superAppleCollision(Position pos, Worm worm) {
		worm.grow(10);
		//GameEngine.boostManager.delete(pos);
	}
}
