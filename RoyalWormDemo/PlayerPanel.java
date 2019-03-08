import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends Container {
    private JLabel name,lives,score,space;
    private int sc, li;
    
    public PlayerPanel(String n)
    {
    	sc = 0;
    	li = 3;
        name = new JLabel(n);
        lives = new JLabel("Lives: " + li);
        score = new JLabel("Score: " + sc);
        space = new JLabel("      ");
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(50,50));
        this.add(name);
        this.add(lives);
        this.add(score);
        this.add(space);
        this.setVisible(true);
    }
    
    
    
    public void update() {
    	for(Player p : GameEngine.playerList) {
    		int setLives = p.getWorm().getLives();
    		int setScore = p.getWorm().getScore();
    		lives.setText("Lives: " + setLives);
    		score.setText("Score: " + setScore);
    	}
    }
}
