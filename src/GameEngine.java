import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameEngine implements Runnable {
	Window window;
	Timer timer;
	final int TIMERDELAY = 200;
	ActionListener aListener;
	
	public GameEngine() 
	{
		this.window = new Window();
		
		timer = new Timer(TIMERDELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
