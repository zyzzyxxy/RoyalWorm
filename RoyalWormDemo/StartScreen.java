import javax.swing.*;
import java.awt.*;

public class StartScreen extends Container {

    public JButton hostButton = new JButton("Host");
    public JButton clientButton = new JButton("Client");

    public StartScreen()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setLayout(new GridLayout(4,4));
        add(hostButton);
        add(clientButton);
    }

}
