import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class StartScreen extends Container {

    public JTextField infoTextfield = new JTextField();
    public JTextField connectToHostTextfield = new JTextField();
    public JButton hostButton = new JButton("Host");
    public JButton clientButton = new JButton("Client, Connect to:");
    boolean active = true;

    public StartScreen() throws UnknownHostException {
        makeStartScreen();


    }

    private void hostButtonPressed()
    {
    }
    private void makeStartScreen()
    {
        this.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        setLayout(new BorderLayout());
        Container middleContainer = new Container();
        Container rightContainer = new Container();

        middleContainer.setLayout(new GridLayout(4,4));

        rightContainer.setLayout(new FlowLayout());
        rightContainer.setPreferredSize(new Dimension(100,Constants.boardHeight));

        Container clientConnectInfo = new Container();
        clientConnectInfo.setLayout(new FlowLayout());

        add(infoTextfield,BorderLayout.NORTH);
        add(middleContainer,BorderLayout.CENTER);
        add(rightContainer,BorderLayout.EAST);


        middleContainer.add(hostButton);
        middleContainer.add(clientButton);

        middleContainer.add(connectToHostTextfield);
        hostButton.addActionListener(e -> {hostButtonPressed();});
    }

}
