package graphCalculator;

//Assignment #: 10

import javax.swing.*;
import java.awt.*;

public class Assignment10 extends JApplet
{
private JTabbedPane MAIN = new JTabbedPane();
private final int WIDTH = 210;
private final int HEIGHT = 280;

public void init()
{
    GamePanel panel = new GamePanel(WIDTH,HEIGHT);
    NormalPanel panel2 = new NormalPanel(WIDTH,HEIGHT);
   
    MAIN.addTab("Calculator",panel2);
    MAIN.addTab("Graph",panel);

    getContentPane().add(MAIN);
    setSize(WIDTH,HEIGHT);
}
}