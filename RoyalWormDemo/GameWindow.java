import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer {
    JMenuBar menuBar;
    JMenu File, Options, Help;
    JMenuItem New, Save, Load, Quit, SetControllers, Gamemode, About;
    GameCanvas gameCanvas;
    Controller controller = new Controller();
    GameEngine gm;

    public GameWindow(GameEngine gm) {
        this.gm = gm;
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
                ((JMenuItem)components[i][j]).addActionListener(e -> {
                    try {
                        menuClicked(e);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                });
    }

    private void menuClicked(ActionEvent e) throws FileNotFoundException {

        System.out.println(e.getActionCommand());


        if(e.getActionCommand().equalsIgnoreCase("New")) {
            gm.resetGameworld(); gameCanvas.repaint();
        }
        if(e.getActionCommand().equalsIgnoreCase("Load"))
            loadFile();
        if(e.getActionCommand().equalsIgnoreCase("Quit"))
            System.exit(0);
    }

    //File must be right size for now
    //Todo make dynamic
    private void loadFile() throws FileNotFoundException {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = jFileChooser.getSelectedFile();
            gm.loadGameworld(file);
        }
        gameCanvas.repaint();
    }
}
