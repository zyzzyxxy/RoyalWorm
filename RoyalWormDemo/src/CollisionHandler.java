import java.awt.Graphics;

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
	public static void collisionHandle(GameObject w, Position pos) throws InterruptedException {
		
		System.out.println("in CH");
		System.out.println(Thread.activeCount());
		if(w instanceof Bullet) {
			System.out.println("Its a bullet");
			switch	(GameEngine.GameWorld[pos.x][pos.y]) {
			case '1': 
				((Bullet)w).setProjection();
				((Bullet)w).getWorm().bullets.remove((Bullet)w);
				System.out.println("removed bullet");
				//cut
				break;
			case 'w':
				((Bullet)w).setProjection();
				((Bullet)w).getWorm().bullets.remove((Bullet)w);
				System.out.println("removed bullet");
				break;
			}
		}
		//This is for worms
		if (w.type == '1' || w.type == '2' || w.type == '3' || w.type == '4' || w.type == '5'  ) {
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
					if(w instanceof Worm) {
					((Worm) w).reset();
					((Worm) w).looseLife();
					System.out.println("wall");
					}
					if(w instanceof Bullet) {
						((Bullet)w).setProjection();
						((Bullet)w).getWorm().bullets.remove((Bullet)w);
						System.out.println("removed bullet");
					}	
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
				case 'g':
					((Worm)w).gun=true;
					break;
				case 'b':
					((Worm) w).reset();
					((Worm) w).looseLife();
				case 'l':
					((Worm) w).addToSpeed(Constants.wormspeed/2);
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {

							System.out.println("Running thread in CH");
							try {
								Thread.sleep(3000);
								System.out.println(Thread.activeCount());
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
	public static void collisionHandle(Bullet bullet, Position headPos) {


	}
}