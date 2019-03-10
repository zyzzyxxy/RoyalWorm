package Canvas;
/**
 * @author Johan Ericsson, Anton Eliasson Gustafsson
 * @version 2019-03-09
 *
 * A panel that displays info about a player.
 */

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends Container {
    private JLabel name,lives,score,space;
    private int sc, li;
    
    public PlayerPanel(String n)
    {
        name = new JLabel(n);
        lives = new JLabel("Lives: 3" );
        score = new JLabel("Score: 0");
        space = new JLabel("      ");
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(50,50));
        this.add(name);
        this.add(lives);
        this.add(score);
        this.add(space);
        this.setVisible(true);
    }
      
    public void update(int livess, int scoree) {
    		lives.setText("Lives: " + livess);
    		score.setText("Score: " + scoree);  	
    }
}
