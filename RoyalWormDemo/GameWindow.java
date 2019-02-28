import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The game window for the host machine
 */


public class GameWindow extends JFrame implements Observer {
    JMenuBar menuBar;
    JMenu File, Options, Help;
    JMenuItem New, Save, Load,Reset, Quit, SetControllers, Gamemode, About;
    GameCanvas gameCanvas;
    StartScreen startScreen;

    GameEngine gm;
    JFrame startWindow;

    public GameWindow(GameEngine gm) throws SocketException{
        this.gm = gm;
        makeFrame();
        gm.addObserver(this);
        setResizable(false);
        gameCanvas.grabFocus();
    }

    @Override
    public void update(Observable o, Object arg) {
        gameCanvas.repaint();
        //List<Change> changes = (List<Change>) arg;
        /*while (!changes.isEmpty())
        {
            //gameCanvas.changes.add(changes.get(0));
            gameCanvas.repaint();
            //changes.remove(0);
        }*/

       /* Change ch;
        while(((Iterator) arg).hasNext())
        {
            ch = ((Change) ((Iterator) arg).next());
            gameCanvas.repaint(ch.x*Constants.gameConstant,ch.y*Constants.gameConstant,Constants.gameConstant,Constants.gameConstant);
        }*/
        //gameCanvas.iterator = (Iterator) arg;



       // GameEngine.changes.clear();
    }
    
    public void infoMenu(){
    	
    
		JFrame frame;
		JPanel info = (JPanel)this.getContentPane();
    	info.setBorder(new EmptyBorder(6, 6, 6, 6));
    	
    	
    }


    private void makeFrame() {
        makeMenus();
        setJMenuBar(menuBar);
        gameCanvas = new GameCanvas();
        gameCanvas.setFocusable(true);
        getContentPane().add(gameCanvas);
        gameCanvas.setBackground(Color.black);
        gameCanvas.repaint();
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void makeMenus()
    {
        File = new JMenu("File"); 
       
        menuBar = new JMenuBar();
        
        menuBar.add(File); 
      /*  menuBar.add(Options); 
        menuBar.add(Help);

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
                });*/
    }

    //Handles JMenu in GameWindow
    private void menuClicked(ActionEvent e) throws IOException {

        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equalsIgnoreCase("New")) {
            gm.resetGameworld(); 
            gameCanvas.repaint();
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
}
