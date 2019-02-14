import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer {
    JMenuBar menuBar;
    JMenu File, Options, Help;
    JMenuItem New, Save, Load, Quit, SetControllers, Gamemode, About;
    GameCanvas gameCanvas;
    Controller controller = new Controller();


    public GameWindow(GameEngine gm) {
        gm.addObserver(this);
        makeFrame();
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("I´m updated");
    }


    private void makeFrame() {
        makeMenus();
        setJMenuBar(menuBar);
        getContentPane().add(gameCanvas = new GameCanvas());
        gameCanvas.setBackground(Color.black);
        gameCanvas.repaint();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void makeMenus()
    {
        File = new JMenu("File"); Options = new JMenu("Options"); Help = new JMenu("Help");
        menuBar = new JMenuBar();
        menuBar.add(File); menuBar.add(Options); menuBar.add(Help);

        File.add(New = new JMenuItem("New"));
        File.add(Save = new JMenuItem("Save"));
        File.add(Load = new JMenuItem("Load"));
        File.add(Quit = new JMenuItem("Quit"));

        Options.add(SetControllers = new JMenuItem("Set Controllers"));
        Options.add(Gamemode = new JMenuItem("Game Mode"));
        Help.add(About = new JMenuItem("About"));

        //Adding actionListener to all JMenuItems
        Component[][] components = {File.getMenuComponents(),Options.getMenuComponents(),Help.getMenuComponents()};
        for(int i=0; i < components.length;i++)
            for(int j=0; j < components[i].length;j++)
                ((JMenuItem)components[i][j]).addActionListener(e -> { menuClicked(e);});
    }

    private void menuClicked(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
