import sun.jvm.hotspot.jdi.ThreadReferenceImpl;

public class CollisionHandler {


	public static boolean collisionCheck(Position pos) 
	{
		if(GameEngine.GameWorld[pos.x][pos.y]!='0') {
			System.out.println("Collision!!");
			return true;
		}
		return false;
	}
	public static void collisionHandle(Worm w, Position pos) throws InterruptedException {
		System.out.println("in CH");
		if (w.type == '1' || w.type == '2' || w.type == '3' || w.type == '4' || w.type == '5') {
			System.out.print("in if");
			switch (GameEngine.GameWorld[pos.x][pos.y]) {

				case '0':
					break;
				case 'a':
					((Worm) w).grow(pos);
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
					((Worm) w).addToSpeed(Constants.wormspeed/2);
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {

							System.out.println("Running thread in CH");
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							((Worm) w).resetSpeed();

						}
					});
					t.start();
					if(!t.isAlive()){
						t.join();
						System.out.println("Joined");
					}

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
