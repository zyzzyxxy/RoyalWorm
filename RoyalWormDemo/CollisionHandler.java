public class CollisionHandler {


	public static boolean collisionCheck(Position pos) 
	{
		if(GameEngine.GameWorld[pos.x][pos.y]!='0') {
			System.out.println("Collision!!");
			return true;
		}
		return false;
	}
	public static void collisionHandle(GameObject w, Position pos) throws InterruptedException {

		if(w instanceof Worm){
		if (w.type == '1' || w.type == '2' || w.type == '3' || w.type == '4' || w.type == '5') {
			System.out.print("in if");
			switch (GameEngine.GameWorld[pos.x][pos.y]) {

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
			}
		}
		}
		else if (w instanceof Ghost)
		{
			System.out.println("Ghost collision");
			for (Player p:GameEngine.playerList) {
				System.out.println(p.name);
				System.out.println(p.worm.isInBody(pos));
				if(p.worm.isInBody(pos)!=-1) {
					p.worm.cut(pos);

				}
			}
		}

	}

}
