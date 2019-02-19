public class CollisionHandler {
	

	public static boolean collisionCheck(Position pos) 
	{
		if(GameEngine.GameWorld[pos.x][pos.y]!='0') {
			System.out.println("Collision!!");
			return true;
		}
		return false;
	}
	public static void collisionHandle(Worm w, Position pos) {
		System.out.println("in CH");
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
			}
		}
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
