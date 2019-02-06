
public class CollisionChecker {
	
	public static char[][] gameWorld;
	private static int wormSize;
	
	public static void init() 
	{
		gameWorld = new char[Constants.gameWidth][Constants.gameHeight];
		wormSize=Constants.wormSize;
		fillGameworld();
	}
	
	public static boolean collisionCheck(Position pos) 
	{
		if(gameWorld[pos.x][pos.y]!='0')
			return true;
		return false;
	}
	
	public static void collisionHandle(GameObject gm, Position pos) 
	{
		System.out.print("in CH");
		if(gm.type=='w') {
			System.out.print("in if");
		switch (gameWorld[pos.x][pos.y]){	
		
			case '0': break;
			case 'a': ((Worm)gm).grow(); System.out.print("ate apple"); break;
			case 'w': ((Worm)gm).reset(); break;
			
		}
		
		}
	
	}
	
	private static void fillGameworld() 
	{
		for(int i =0; i<Constants.gameWidth;i++)
			for(int j =0; j<Constants.gameHeight;j++)
				gameWorld[i][j]='0';
	}
	public static void updateGameworld(Position pos, char c) 
	{
		gameWorld[pos.x][pos.y]=c;
	}

}
