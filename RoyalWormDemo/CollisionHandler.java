
public class CollisionHandler {

	public CollisionHandler() {

	}
	
	public boolean collisionCheck(Position pos) {
		if (pos.getX() < 0 || pos.getX() > 79 || pos.getY() < 0 || pos.getY() > 58) {
			return true;
		}
		return GameEngine.GameWorld[pos.getX()][pos.getY()] != '0';
	}
	
	public void collisionHandle(Worm worm, Position pos) {
		if (pos.getX() < 0 || pos.getX() > 79 || pos.getY() < 0 || pos.getY() > 58) {
			wormToWorm(worm);
		} else {
			char posValue = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if (posValue == '1' || posValue == '2') {
				wormToWorm(worm);
			}
			
			if (posValue == 'a') {
				wormToApple(pos, worm);
			}
		}
	}
	
	private void wormToWorm(Worm worm) {
		worm.stop();
		//TODO give points to other players?
	}
	
	private void wormToApple(Position pos, Worm worm) {
		worm.grow();
		GameEngine.BM.delete(pos);
	}
}
