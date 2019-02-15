import javax.swing.*;
import java.awt.*;

public class StartScreen extends Container {

    JButton hostButton = new JButton("Host");
    JButton clientButton = new JButton("Client");

    public StartScreen()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setLayout(new GridLayout(4,4));
        add(hostButton);
        add(clientButton);
    }

}
