

//Assignment #: 10

import javax.swing.*;
import java.awt.*;

public class mainClass extends JApplet
{
private JTabbedPane MAIN = new JTabbedPane();
 public static int WIDTH = 210;
 public static int HEIGHT = 280;
 CalcModel model;
 CalcController control = new CalcController();

public void init()
{
	System.out.println("mainClass");

    GraphPanel panel = new GraphPanel(WIDTH,HEIGHT);
    CalcModel panel2 = new CalcModel();
    CalcView panel3 = new CalcView(control);
    

    MAIN.addTab("Calculator", panel3);
    MAIN.addTab("Graph",panel);

    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}