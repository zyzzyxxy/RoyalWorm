package Canvas;
/**
 * @author Johan Ericsson, Anton Eliasson Gustafsson
 * @version 2019-03-09
 *
 * A panel that displays info (name, lives and score) about a player.
 */

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends Container {
    private JLabel name,lives,score,space;
    private int sc, li;
    
    /**
     * Instantiates a PlayerPanel for a player.
     * @param n is the name of the player. 
     */
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
      /**
       * Updates a players PlayerPanel.
       * @param livess updates a player's lives.
       * @param scoree updates a player's score.
       */
    public void update(int livess, int scoree) {
    		lives.setText("Lives: " + livess);
    		score.setText("Score: " + scoree);  	
    }
}
