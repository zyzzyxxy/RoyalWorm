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
					((Worm) w).setScore(((Worm) w).getScore()+5);
					break;
				case 's':
					((Worm) w).grow(10);
					((Worm) w).setScore(((Worm) w).getScore()+10);
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
					((Worm) w).setScore(((Worm) w).getScore()+1);
					break;
				case 'p':
					((Worm) w).pickUpGun();
					((Worm) w).setScore(((Worm) w).getScore()+3);
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
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if(comp == 'w') {
				((Ghost)w).setDead(true);
			}
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
			Worm tmp = ((Bullet)w).getWorm();
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if(comp =='w' || comp == 'b'|| comp == '1' || comp == '2' || comp == '3' || comp == '4' || comp == '5' || comp == 'g' || comp == 'a' || comp == 's'){
				if(comp == 'w')
				((Bullet)w).endProjection();
				if(comp == 's')
					tmp.setScore(tmp.getScore() + 10);
				if(comp == 'a')
					tmp.setScore(tmp.getScore() + 5);
				if(comp == 'g')
					tmp.setScore(tmp.getScore() + 50);
				if(comp == 'p')
					tmp.setScore(tmp.getScore() + 3);
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
