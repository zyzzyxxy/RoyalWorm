import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameEngine implements Runnable {
	Window window;
	Timer timer;
	final int TIMERDELAY = 100;
	ActionListener aListener;
	private final int APPLESPAWN =1;
	private int appleCounter;
	public final int BOARDWIDTH =800;
	public final int BOARDHEIGHT =600;
	
	
	public GameEngine() 
	{
		CollisionChecker.init();
		this.window = new Window();
		appleCounter=0;
		
		timer = new Timer(TIMERDELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateAll();
			}
		});
	}

	@Override
	public void run() {
		timer.start();		
	}
	
	public void updateAll() 
	{
		appleUpdate();
		window.update();
	}
	public void appleUpdate()
	{
		appleCounter++;
		if(appleCounter==APPLESPAWN) {
			window.gameContainer.spawn("apple");
			appleCounter=0;
		}
	}

}
