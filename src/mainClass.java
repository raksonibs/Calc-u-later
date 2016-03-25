

//Assignment #: 10

import javax.swing.*;
import java.awt.*;

public class mainClass extends JApplet
{
private JTabbedPane MAIN = new JTabbedPane();
 public static int WIDTH = 800;
 public static int HEIGHT = 400;
 CalcModel model;
 CalcController control = new CalcController();

public void init()
{
	System.out.println("mainClass");

    GraphPanel panel = new GraphPanel(WIDTH,HEIGHT);
    FavouritesPanel panel2 = new FavouritesPanel();
    CalcView panel3 = new CalcView(control);
    

    MAIN.addTab("Calculator", panel3);
    MAIN.addTab("Graph",panel);
    MAIN.addTab("Favourites", panel2);

    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}