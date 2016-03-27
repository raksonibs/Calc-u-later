import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class mainClass extends JApplet
{
private JTabbedPane MAIN = new JTabbedPane();
 public static int WIDTH = 800;
 public static int HEIGHT = 450;
 CalcModel model;
 GraphModel graphModel;
 CalcController calcControl = new CalcController();
 GraphController graphControl = new GraphController();
 FavouritesPanel panel2;

public void init()
{
	System.out.println("mainClass");

    panel2 = new FavouritesPanel();
    CalcView calculatorView = new CalcView(calcControl);
    GraphView graphView = new GraphView(graphControl);
    

    MAIN.addTab("Calculator", calculatorView);
    MAIN.addTab("Graph",graphView);
    MAIN.addTab("Favourites", panel2);
    
    MAIN.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            System.out.println("Tab: " + MAIN.getSelectedIndex());
            if (MAIN.getSelectedIndex() == 2)
            {
            	for (String s: calcControl.a)
            	{
            			panel2.addToFavourites(s);
            	}
            }
            else if (MAIN.getSelectedIndex() == 0){panel2.clearList();panel2.addToFavourites("Select Favourite...");}
        }
    });
  
    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}