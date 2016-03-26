
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphPanel extends JPanel implements ActionListener
 {
   private int width, height;
   JPanel graph = new JPanel();
   Color color;
   JTextField Function;
   Graph temp;
   
   public GraphPanel(int width1, int height1)
    {
       this.width = width1;
       this.height = height1;
       Function = new JTextField();
       JButton startR = new JButton("Graph"); startR.addActionListener(this);
       startR.setSize(new Dimension(10,10));
       JPanel Buttons = new JPanel(new GridLayout(1,2));      
       Buttons.setPreferredSize(new Dimension(500,20));
       Buttons.add(Function);
       Buttons.add(startR);
       temp = new Graph();
       graph.add(temp);

      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
   
       add(graph);
       add(Buttons);
       //set preferred size of this panel
       setPreferredSize(new Dimension(width,height));
    }


      public void actionPerformed(ActionEvent event)
        {
           String input = Function.getText().trim();
           temp.graphs(-10,-10,10,10,color.blue,input);

        } //end of actionPerformed method
  
} //end of ControlPanel class

