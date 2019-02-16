import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer {
    JMenuBar menuBar;
    JMenu File, Options, Help;
    JMenuItem New, Save, Load, Quit, SetControllers, Gamemode, About;
    GameCanvas gameCanvas;
    StartScreen startScreen;
    //Controller controller = new Controller();
    GameEngine gm;

    public GameWindow(GameEngine gm) throws SocketException {
        this.gm = gm;
        makeFrame();
        gm.addObserver(this);

    }


    @Override
    public void update(Observable o, Object arg) {
        gameCanvas.repaint();
    }


    private void makeFrame() {
        makeMenus();
        setJMenuBar(menuBar);
        getContentPane().add(startScreen = new StartScreen());
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
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
    }

    private void menuClicked(ActionEvent e) throws IOException {

        System.out.println(e.getActionCommand());


        if(e.getActionCommand().equalsIgnoreCase("New")) {
            startScreen.setVisible(false);
            gm.resetGameworld(); gameCanvas.repaint();

        }
        if(e.getActionCommand().equalsIgnoreCase("Save"))
            loadFile();
        if(e.getActionCommand().equalsIgnoreCase("Load"))
            loadFile();
        if(e.getActionCommand().equalsIgnoreCase("Quit"))
            System.exit(0);
    }

    //File must be right size for now
    //public just for testing!
    //Todo make dynamic
    private void loadFile() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        String current = new java.io.File( "." ).getCanonicalPath();
        jFileChooser.setCurrentDirectory(new File(current));
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = jFileChooser.getSelectedFile();
            gm.loadGameworld(file);
        }
        gameCanvas.repaint();
    }

    //Todo get writable option in window
    private void saveFile() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        String current = new java.io.File( "." ).getCanonicalPath();
        jFileChooser.setCurrentDirectory(new File(current));
        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = jFileChooser.getSelectedFile();
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file);
            for (char[] c:GameEngine.GameWorld) {
                fw.write(new String(c));
                fw.write("\n");
            }
        }
        gameCanvas.repaint();
    }

    //Just for testing
    public void loadFile(File file) throws IOException {
        gm.loadGameworld(file);
        gameCanvas.repaint();
    }
}
