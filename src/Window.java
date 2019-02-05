import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Paint;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	Container baseContainer;
	GameCanvas gameContainer;
	JMenuBar menuBar;
	Image gameImage;
	
	
	public Window() 
	{
		
		gameContainer = new GameCanvas();
		baseContainer = this.getContentPane();
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new FlowLayout());
		sideBar.setPreferredSize(new Dimension(75,750));
		
		gameContainer.setPreferredSize(new Dimension(800,600));
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000,800));
		gameContainer.setBackground(Color.red);
		baseContainer.setBackground(Color.blue);
		baseContainer.setLayout(new BorderLayout());

		JButton button= new JButton("Hej");
		//testpanel.setBackground(Color.black);
		
		makeMenus();
		this.pack();
		//baseContainer.add(gameContainer,BorderLayout.CENTER);
		baseContainer.add(gameContainer,BorderLayout.CENTER);
		baseContainer.add(sideBar,BorderLayout.EAST);
		gameContainer.setBackground(Color.black);
		gameContainer.grabFocus();

	}
	
	

	private void makeMenus() 
	{
		//Making menus
				menuBar =  new JMenuBar();
				JMenu fileMenu = new JMenu("File");
				JMenu optionMenu = new JMenu("Options");
				JMenu helpMenu = new JMenu("Help");
				
				JMenuItem newGame = new JMenuItem("New Game");
				newGame.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showConfirmDialog(null, "New Game Starting...");				
					}
				});
				JMenuItem qGame = new JMenuItem("Quit");
				qGame.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
				});
				JMenuItem oNone = new JMenuItem("Nothing here");

				JMenuItem hNone = new JMenuItem("Don´t ask");
				
				fileMenu.add(newGame);
				fileMenu.add(qGame);
				optionMenu.add(oNone);
				helpMenu.add(hNone);
				
				menuBar.add(fileMenu);
				menuBar.add(optionMenu);
				menuBar.add(helpMenu);

				baseContainer.add(menuBar,BorderLayout.NORTH);
	}
	
	public void update() 
	{
		gameContainer.update();
	}
}
