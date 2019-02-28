import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends Container {

    JLabel name,lives,score,space;

    public PlayerPanel(String n)
    {
        name = new JLabel(n);
        lives = new JLabel("Lives: 3");
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

}
