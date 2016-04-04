import java.awt.Dimension;

import javax.swing.JFrame;

public class CalcMVC 
{
    public static void main(String[] args) 
    {
        mainClass run = new mainClass();
        	run.setVisible(true);
        	run.setSize(new Dimension(430,525));
        	run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
