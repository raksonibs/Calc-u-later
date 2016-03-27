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
 CalcController control = new CalcController();
 FavouritesController favC;
 GraphController graphControl = new GraphController();
 FavouritesPanel panel2;

public void init()
{
	System.out.println("mainClass");

	
	 CalcModel model = new CalcModel(MAIN);
	 final GraphModel graphModel = new GraphModel("");
	 
	 final CalcController calcControl = new CalcController(model, graphModel, MAIN);
	 final GraphController graphControl = new GraphController(model, graphModel);
	
    panel2 = new FavouritesPanel(MAIN);
    CalcView calculatorView = new CalcView(calcControl, MAIN);
    final GraphView graphView = new GraphView(graphControl);

    MAIN.addTab("Calculator", calculatorView);
    MAIN.addTab("Graph", graphView);

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
            else if (MAIN.getSelectedIndex() == 0)
            {
            	panel2.clearList();
                panel2.addToFavourites("Select Favourite...");
                panel2.switchToGraph = false;
            } else if ( MAIN.getSelectedIndex() == 1) {
            	graphView.updateGraph(graphModel.getChartPanel());
            }            
        }
    });
  
    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}