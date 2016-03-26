import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class mainClass extends JApplet
{
private JTabbedPane MAIN = new JTabbedPane();
 public static int WIDTH = 800;
 public static int HEIGHT = 400;
 CalcModel model;
 CalcController control = new CalcController();
 FavouritesController favC;
 FavouritesPanel panel2;

public void init()
{
	System.out.println("mainClass");

    GraphPanel panel = new GraphPanel(WIDTH,HEIGHT);
    panel2 = new FavouritesPanel();
    CalcView panel3 = new CalcView(control);
    favC = new FavouritesController();
    
    
    MAIN.addTab("Calculator", panel3);
    MAIN.addTab("Graph",panel);
    MAIN.addTab("Favourites", panel2);
    
    MAIN.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            System.out.println("Tab: " + MAIN.getSelectedIndex());
            if (MAIN.getSelectedIndex() == 2)
            {
            	for (String s: favC.a)
            	{
            			panel2.addToFavourites(s);
            	}
            }
            else if (MAIN.getSelectedIndex() == 0 || MAIN.getSelectedIndex() == 1)
            {panel2.clearList();panel2.addToFavourites("Select Favourite...");}
        }
    });
  
    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}