package graphCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NormalPanel extends JPanel implements KeyListener
 {

   private int width, height;
   double variable=69;
   Color color;
   Graph temp;
   TextArea INPUT = new TextArea("",12,25,1);
   JTextField box = new JTextField();
   public NormalPanel(int width1, int height1)
    {
       this.width = width1;
       this.height = height1;
       temp = new Graph();
       INPUT.setEditable(false);
       INPUT.setBackground(new Color(0,0,0));
       INPUT.setForeground(new Color(255,255,255));
       box.setBackground(new Color(0,0,0));
       box.setSelectionColor(new Color(255,255,255));
       box.setSelectedTextColor(new Color(0,0,0));
       box.setForeground(new Color(255,255,255));
       box.setCaretColor(new Color(255,0,255));
       box.addKeyListener(this);setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
       add(INPUT);
       add(box);
       
      requestFocus();

       //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
       //set preferred size of this panel
       setPreferredSize(new Dimension(width,height));
    }


         public void keyReleased(KeyEvent e){}
         public void keyTyped(KeyEvent e){}

          public void keyPressed(KeyEvent e)
           {  
	String input = box.getText().toLowerCase().trim();
	if(input.compareTo("cls")==0){INPUT.setText("");box.setText("");input="";}
	   if(input.length()>=1)
	     if(e.getKeyCode()==10)
		{
			if(input.lastIndexOf(">")>=0)
				{input = input.substring(0,input.lastIndexOf(">"));input=temp.Operation(input,variable);
				  variable = temp.number(input,variable);}
			INPUT.append("\n"+input+"\nANS=");
			input = temp.Operation(input,variable);
			input = String.valueOf(temp.number(input,variable));
			INPUT.append(input);
			box.setText("");
		}	
           }
    public boolean isFocusable()
     {
        return true;
     }
} //end of ControlPanel class






