public class CollisionHandler {


	public static boolean collisionCheck(Position pos) 
	{
		if(GameEngine.GameWorld[pos.getX()][pos.getY()]!='0') {
			System.out.println("Collision!!");
			return true;
		}
		return false;
	}
	public static void collisionHandle(GameObject w, Position pos) throws InterruptedException {

		if(w instanceof Worm){
		if (w.getType() == '1' || w.getType() == '2' || w.getType() == '3' || w.getType() == '4' || w.getType() == '5') {
			System.out.print("in if");
			switch (GameEngine.GameWorld[pos.getX()][pos.getY()]) {

				case '0':
					break;
				case 'a':
					((Worm) w).grow();
					break;
				case 's':
					((Worm) w).grow(10);
					break;
				case 'w':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case '1':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case '2':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case '3':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case '4':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case '5':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
				case 'l':
					//((Worm) w).addToSpeed(Constants.wormspeed/2);
					((Worm) w).lightninhMode();
					break;
				case 'p':
					((Worm) w).pickUpGun();
					break;
				case 'b':
					((Worm) w).reset();
					((Worm) w).looseLife();
					break;
			}
		}
		}
		else if (w instanceof Ghost)
		{
			System.out.println("Ghost collision");
			for (Player p:GameEngine.playerList) {
				System.out.println(p.getName());
				System.out.println(p.getWorm().isInBody(pos));
				if(p.getWorm().isInBody(pos)!=-1) {
					p.getWorm().cut(pos);
					((Ghost)w).setDead(true);
				}
			}
		}
		else if(w instanceof Bullet) {
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			//((Bullet)w).endProjection();
			if(comp =='w' || comp == 'b'|| comp == '1' || comp == '2' || comp == '3' || comp == '4' || comp == '5'){
				((Bullet)w).endProjection();
			}
			else 
			
			for (Player p:GameEngine.playerList) {
				if(p.getWorm().isInBody(pos)!=-1) {
					p.getWorm().cut(pos);
				}
			}

		}

	}

}
