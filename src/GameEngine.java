import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameEngine implements Runnable {
	Window window;
	Timer timer;
	final int TIMERDELAY = 100;
	ActionListener aListener;
	private final int APPLESPAWN =10;
	private int appleCounter;
	public final int BOARDWIDTH =800;
	public final int BOARDHEIGHT =600;

	
	public GameEngine() 
	{
		this.window = new Window();
		appleCounter=0;
		
		timer = new Timer(TIMERDELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appleCounter++;
				if(appleCounter==APPLESPAWN) {
					window.gameContainer.spawn("apple");
					appleCounter=0;
				}
				
				
				window.update();
				System.out.print("asd");
			}
		});
		

		
	}

	@Override
	public void run() {
		timer.start();		
	}

}
