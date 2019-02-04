import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	Container baseContainer;
	Component menuBar;
	List<JMenu> menu;
	
	public Window() 
	{
		Container container =  new Container();
		container.setLayout(new BorderLayout());
		baseContainer = this.getContentPane();
		container.setPreferredSize(new Dimension(800,600));
		baseContainer.add(container);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		
		JButton button= new JButton();
		this.menuBar = new JMenuBar();
		this.menu = new ArrayList<JMenu>();
		PopupMenu file =  new PopupMenu("File");
		//this.menu.add(file);
		//this.menu.add(new JMenu("Options"));
		//this.menu.add(new JMenu("Help"));
		Iterator<JMenu> menuIt = menu.iterator();
		//while(menuIt.hasNext())
		//	menuBar.add()
		menuBar.add(file);
		baseContainer.add(menuBar);
		System.out.print(menuBar.isVisible());
		container.add(menuBar,BorderLayout.NORTH);
		this.pack();

		

	}

}
