package Windows;
import javax.swing.*;

import BoardObjects.Player;
import Canvas.GameCanvas;
import GameHandling.GameEngine;
import src.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

/**
 * The game window for the host machine
 * @author Anton Eliasson Gustafsson
 * @version 2019-03-09
 */
public class GameWindow extends JFrame implements Observer {
	public GameCanvas gameCanvas;
	
    private JMenuBar menuBar;
    private JMenu File, Options, Help;
    private JMenuItem New, Save, Load,Reset, Quit, SetControllers, Gamemode, About;
    private GameEngine gm;
    private Container playerContainer;

    /**
     * Constructor for GameWindow. Saves a reference to GameEngine and initiates the JFrame.
     * 
     * @param gm The gameWorld for the program.
     */
    public GameWindow(GameEngine gm) throws SocketException {
        this.gm = gm;
        makeFrame();
        gm.addObserver(this);
        setResizable(false);
        gameCanvas.grabFocus();
        gameCanvas.setBackground(Color.black);
        gameCanvas.repaint();
    }

    /**
     * Draws the observed change.
     */
    @Override
    public void update(Observable o, Object arg) {
        gameCanvas.paint(gameCanvas.getGraphics());
    	for(Player p : GameEngine.playerList)
    		playerContainer.add(p.getPlayerPanel());
        
    }

    /**
     * Initiates the JFrame.
     */
    private void makeFrame() {
        makeMenus();
        setJMenuBar(menuBar);
        this.getContentPane().setLayout(new BorderLayout());
        gameCanvas = new GameCanvas();
        gameCanvas.setFocusable(true);
        getContentPane().add(gameCanvas,BorderLayout.CENTER);
        gameCanvas.setBackground(Color.black);
        gameCanvas.repaint();

        playerContainer = new Container();
        playerContainer.setLayout(new FlowLayout());
        for (Player p : GameEngine.playerList) {
            playerContainer.add(p.getPlayerPanel());
        }
        this.getContentPane().add(playerContainer,BorderLayout.NORTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Creates a JMenu containing options with actionListeners.
     */
    private void makeMenus() {
        File = new JMenu("File"); Options = new JMenu("Options"); Help = new JMenu("Help");
        menuBar = new JMenuBar();
        menuBar.add(File); menuBar.add(Options); menuBar.add(Help);

        File.add(New = new JMenuItem("New"));
        File.add(Save = new JMenuItem("Save"));
        File.add(Load = new JMenuItem("Load"));
        File.add(Reset = new JMenuItem("Reset"));
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


    /**
     * Handles JMenu in GameWindow with the use of ActionEvent.
     */
    private void menuClicked(ActionEvent e) throws IOException {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equalsIgnoreCase("New")) {
            gm.resetGameworld(); gameCanvas.repaint();
        }
        if(e.getActionCommand().equalsIgnoreCase("Save"))
            saveFile();
        if(e.getActionCommand().equalsIgnoreCase("Load"))
            loadFile();
        if(e.getActionCommand().equalsIgnoreCase("Reset"))
            gm.resetGameworld(); gameCanvas.repaint();
        if(e.getActionCommand().equalsIgnoreCase("Quit"))
            System.exit(0);
    }

    /**
     * Loads a file containing a gameWorld.
     */
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

    /**
     * Saves a copy of the current gameWorld.
     */
    private void saveFile() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        String current = new java.io.File( "." ).getCanonicalPath();
        jFileChooser.setCurrentDirectory(new File(current));
        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = jFileChooser.getSelectedFile();
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file);
                for (int i = 0; i < Constants.worldHeight; i++) {
                    for (int j = 0; j < Constants.worldWidth; j++){
                        fw.write(GameEngine.GameWorld[j][i]);
                        }
                    fw.write("\n");
                    }
                fw.close();
                }
        }
}
