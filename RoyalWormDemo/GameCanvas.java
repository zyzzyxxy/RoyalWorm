import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    public GameCanvas()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setBackground(Constants.backgroundColor);
    }
}
