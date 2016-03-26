import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class mainClass extends JApplet
{
 private JTabbedPane MAIN = new JTabbedPane();
 public static int WIDTH = 800;
 public static int HEIGHT = 450;



 FavouritesController favC;
 FavouritesPanel panel2;

public void init()
{
	System.out.println("mainClass");
	
	 CalcModel model = new CalcModel();
	 GraphModel graphModel = new GraphModel("");
	 
	 final CalcController calcControl = new CalcController(model, graphModel);
	 final GraphController graphControl = new GraphController(model, graphModel);
	
    panel2 = new FavouritesPanel();

    // CalcView calculatorView = new CalcView(calcControl);
    // GraphView graphView = new GraphView(graphControl);

    // MAIN.addTab("Calculator", calculatorView);
    // MAIN.addTab("Graph",graphView);

    CalcView panel3 = new CalcView(control);
    
    JPanel graphPanel = new JPanel();
    graphPanel.add(graphControl.getChartPanel());
    favC = new FavouritesController();
    
    MAIN.addTab("Calculator", panel3);
    MAIN.addTab("Graph",graphPanel);

    MAIN.addTab("Favourites", panel2);
    
    MAIN.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        	graphControl.updateGraph();
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