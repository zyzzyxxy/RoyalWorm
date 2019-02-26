
public class CollisionHandler {
    private static int squareWidth = (Constants.boardWidth/Constants.gameConstant) - 2;
    private static int squareHeight = (Constants.boardHeight/Constants.gameConstant) - 2;
	
	
	public CollisionHandler() {

	}
	
	public static boolean collisionCheck(Position pos) {
		if (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight) {
			return true;
		}
		System.out.printf("posX: %d", pos.getX());
		return GameEngine.GameWorld[pos.getX()][pos.getY()] != '0';
	}

	public static void collisionHandle(Worm worm, Position pos) {
		if (pos.getX() < 0 || pos.getX() > squareWidth || pos.getY() < 0 || pos.getY() > squareHeight) {
			wormToWorm(worm);
		} else {
			char posValue = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if (posValue == 'W' || posValue == '1' || posValue == '2') {
				wormToWorm(worm);
			}
			
			if (posValue == 'a') {
				wormToApple(pos, worm);
			}

			if (posValue == 's') {
				wormToS(pos, worm);
			}
			
			if (posValue == 'l') {

			}
		}
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

	//For when worm extends GameObject
	/*
	public static void collisionHandle(GameObject gm, Position pos) 
	{
		System.out.println("in CH");
		if(gm.type=='w') {
			System.out.print("in if");
		switch (GameEngine.GameWorld[pos.x][pos.y]){
		
			case '0': break;
			case 'a': ((Worm)gm).grow(); System.out.print("ate apple"); break;
			case 'w': ((Worm)gm).reset(); break;
		}
		}
	}*/

}
