//import graphCalculator.Graph;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.IOException;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CalcView extends JPanel implements KeyListener
{
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	static int buttonClicked = 0;

	public static GraphPanel panel2 = new GraphPanel(400,400);
	private static final long serialVersionUID = -8730447125113729547L;

	private static JTextField userValueText;
	private static JTextField calcText;
	private static JTextField history;
	private static JTextField expressionList;
	private static FavouritesController favC =  new FavouritesController();

	//private static JFreeChart chart;
	private static JComboBox<String> box;
	//private static ChartPanel chartPanel;

	 public void keyReleased(KeyEvent e){}
     public void keyTyped(KeyEvent e){}

     public void keyPressed(KeyEvent e){}

	
	@SuppressWarnings("serial")
	public CalcView(final CalcController theController, JTabbedPane MAIN)
	{
		super();
		System.out.println("Test");
		addComponentsToPane(this, theController, MAIN);

		requestFocus();

		this.setVisible(true);

	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridBagLayout());
        panel.add(filler);
        return panel;
    }
     
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CalcView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }



	@SuppressWarnings("serial")
	public static void addComponentsToPane(Container pane, final CalcController theController, final JTabbedPane MAIN)
	{
		if (RIGHT_TO_LEFT)
		{
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
		{
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		calcText = new JTextField(20);
		calcText.setEditable(false);
		userValueText = new JTextField(5);
		history = new JTextField(20);
		history.setEditable(false);
		history.setText("Start a new calculation");
		expressionList = new JTextField(20);
		expressionList.setEditable(false);
		
		if (shouldWeightX) {
			c.weightx = 0.5;
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		pane.add(calcText, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2;
		pane.add(new JLabel("Calculated Value"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		pane.add(history, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 2;
		pane.add(new JLabel("History"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		pane.add(expressionList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		pane.add(new JLabel("Expression"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		pane.add(userValueText, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 2;
		pane.add(new JLabel("Input"), c);

		button = new ButtonAdapter("UNDO")
		{
			public void pressed()
			{
				registerButton("UNDO", theController);
			}
		};

		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);

		button = new ButtonAdapter("π")
		{
			public void pressed()
			{
				changeInputButton(Math.PI);

			}
		};
		
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);
		
		button = new ButtonAdapter("!")
		{
			public void pressed()
			{
				registerButton("!", theController);
			}
		};

		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);

		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);

		button = new ButtonAdapter("sin")
		{
			public void pressed()
			{
				registerButton("sin", theController);
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);

		button = new ButtonAdapter("cos")
		{
			public void pressed()
			{
				registerButton("cos", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);

		button = new ButtonAdapter("INFO")
		{
			public void pressed()
			{
				registerButton("INFO", theController);
			}
		};

		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);		

		// Adding SAVE and DELETE buttons related to favourites list.
		button = new ButtonAdapter("SAVE")
		{
			public void pressed()
			{
				registerButton("SAVE", theController);
			}
		};

		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);				

		button =  new ButtonAdapter("Clear") {public void pressed(){ theController.clear();}};		

		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);	

		JButton button0 = new ButtonAdapter("" + 0)
		{
			public void pressed()
			{
				changeInputButton(0);
			}
		};

		button0.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button0, c);

		JButton button1 = new ButtonAdapter("" + 1)
		{
			public void pressed()
			{
				changeInputButton(1);
			}
		};

		button1.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button1, c);

		JButton button2 = new ButtonAdapter("" + 2)
		{
			public void pressed()
			{
				changeInputButton(2);
			}
		};

		button2.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button2, c);

		button = new ButtonAdapter("+")
		{
			public void pressed()
			{
				registerButton("+", theController);
			}
		};

		button.setBackground(Color.BLUE);
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);

		button = new ButtonAdapter("-")
		{
			public void pressed()
			{
				registerButton("-", theController);
			}
		};

		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);

		button = new ButtonAdapter("x")
		{
			public void pressed()
			{
				registerButton("x", theController);
			}
		};

		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);

		button = new ButtonAdapter("÷")
		{
			public void pressed()
			{
				registerButton("÷", theController);
			}
		};

		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);

		JButton button3 = new ButtonAdapter("" + 3)
		{
			public void pressed()
			{
				changeInputButton(3);
			}
		};

		button3.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button3, c);

		JButton button4 = new ButtonAdapter("" + 4)
		{
			public void pressed()
			{
				changeInputButton(4);
			}
		};

		button4.setBackground(Color.BLUE);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button4, c);

		JButton button5 = new ButtonAdapter("" + 5)
		{
			public void pressed()
			{
				changeInputButton(5);
			}
		};

		button5.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button5, c);

		JButton button6 = new ButtonAdapter("" + 6)
		{
			public void pressed()
			{
				changeInputButton(6);
			}
		};

		button6.setBackground(Color.BLUE);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button6, c);

		JButton button7 = new ButtonAdapter("" + 7)
		{
			public void pressed()
			{
				changeInputButton(7);
			}
		};

		button7.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button7, c);

		JButton button8 = new ButtonAdapter("" + 8)
		{
			public void pressed()
			{
				changeInputButton(8);
			}
		};

		button8.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button8, c);

		JButton button9 = new ButtonAdapter("" + 9)
		{
			public void pressed()
			{
				changeInputButton(9);
			}
		};

		button9.setBackground(Color.BLUE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button9, c);

		button = new ButtonAdapter("+/-")
		{
			public void pressed()
			{
				registerButton("+/-", theController);
			}
		};

		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);

		button = new ButtonAdapter(".")
		{
			public void pressed()
			{
				registerButton(".", theController);
			}
		};

		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);
		
		button = new ButtonAdapter("X"){
			public void pressed(){
				registerButton("X", theController);
			}
		};

		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 11;
		pane.add(button, c);
		
		button = new ButtonAdapter("Graph"){
			public void pressed() {
				MAIN.setSelectedIndex(1);
				registerButton("Graph", theController);
			}
		};

		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 11;
		pane.add(button, c);

		button = new ButtonAdapter("Enter") {public void pressed(){ addToHistory( theController );}};
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 4;   //2 columns wide
		c.gridy = 10;       //third row
		pane.add(button, c);
	}

	/**
	 * Get the string value of the user input text field.
	 * 
	 * @return The string in the user input text field.
	 */
	public static String getUserValue()
	{
		String userValue = userValueText.getText();
		System.out.println("User Value is: " + userValue);
		return userValue;

	}

	
	/**
	 * Check to see if the user has entered a value into the text field
	 * 
	 * @return false if the text field is empty
	 */
	public boolean containsUserValue()
	{
		if (userValueText.getText().equals(""))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public static void registerButton(String button,
			CalcController theController)
	{
		String his = history.getText();		

		if (button.equals("UNDO"))
		{
			System.out.println("UNDO");
			if (userValueText.getText().equals(""))
			{
				theController.undo();
			}
			else
			{
				String text = userValueText.getText();
				int length = userValueText.getText().length();
				text = text.substring(0, length - 1);
				userValueText.setText(text);
			}
		}

		if (button.equals("+"))
		{			
			System.out.println("addition");
			theController.sum();
			userValueText.setText("");

		}
		else if (button.equals("-"))
		{
			System.out.println("subtract");
			theController.subtract();
			userValueText.setText("");

		}
		else if (button.equals("x"))
		{
			System.out.println("multiplty");
			theController.multiply();
			userValueText.setText("");
		}
		else if (button.equals("÷"))
		{
			System.out.println("divide");
			theController.divide();
			userValueText.setText("");
		}

		else if (button.equals("TEST")) {
			System.out.println("Inputting test case");	
			theController.runTestCase();
			
		}
		else if (button.equals("INFO")) {
		//System.out.println("Printing Stack information");			
			theController.printInfoToConsole();			
		}	

		else if (button.equals("Graph")) {
		
			theController.updateGraph();
		}	

		else if (button.equals("SAVE"))
		{
			System.out.println("SAVING...");
			favC.saving(expressionList.getText().toString());
		}
		
		// fixed negate button
		else if (button.equals("+/-"))
		{

			String userVal = userValueText.getText();
			char changeSign = userVal.charAt(0);
			if (!userVal.equals("0"))
			{
				if (changeSign == '-')
				{
					userVal = userVal.substring(1, userVal.length());
					userValueText.setText(userVal);
				}
				else
				{
					userVal = '-' + userVal;
					userValueText.setText(userVal);
				}
			}

		}
		else if (button.equals("."))
		{

			String userVal = userValueText.getText();

			if (userVal.length() > 1 && userVal.charAt(1) == '.')
			{
				userVal = userVal.substring(1, his.length());
				userValueText.setText(userVal);
			}
			else
			{

				if (userVal.length() > 0)
				{
					userVal = userVal + ".";
				}
				else
				{
					userVal = "0." + userVal;
				}
				userValueText.setText(userVal);
			}

		}
		else if (button.equals("sin"))
		{

			System.out.print("Sin of ");

			theController.sin();

			userValueText.setText("");
		}

		else if (button.equals("cos"))
		{

			System.out.print("Cos of ");

			theController.cos();

			userValueText.setText("");
		}

		else if (button.equals("!"))
		{

			System.out.println("factorial");
			theController.factorial();
			userValueText.setText("");

		}
		else if (button.equals("X")){

			System.out.println("Inputed variable");
			theController.variable();
			userValueText.setText("");

		}

		else if (button.equals("UNDO"))
		{

			// if(userValueText.)
			System.out.println("undo test");
			if (userValueText.getText().isEmpty())
			{
				System.out.println("text is not empty");

				userValueText.setText("");
			}

		}

	}

	// This method to handle integers
	/**
	 * Enters the value of the button pressed into the text field
	 * 
	 * @param buttonInput
	 */
	public static void changeInputButton(int buttonInput)
	{

		String value = String.valueOf(buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();

		if (history.getText().equals("Start a new calculation"))
		{
			history.setText("");
		}

	}

	// Added to handle doubles such as pi
	/**
	 * Enters the value of the button pressed into the text field
	 * 
	 * @param buttonInput
	 */
	public static void changeInputButton(double buttonInput)
	{

		// Round to 5 digits
		String value = String.format("%.5f", buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();

		if (history.getText().equals("Start a new calculation"))
		{
			history.setText("");
		}

	}

	/**
	 * Sends the inputed number to the controller
	 * 
	 * @param theController
	 */
	public static void addToHistory(CalcController theController)
	{
		String value = history.getText();

		double val = Double.parseDouble(userValueText.getText());

		System.out.println("" + val);

		BigDecimal allValue = new BigDecimal(val);
		theController.addToRounding(userValueText.getText());
		theController.addValue(allValue);

		userValueText.setText("");

	}

	public static void setButtonClicked()
	{
		buttonClicked = 0;
	}

	/**
	 * Set the string for the user input text field.
	 * 
	 * @param value
	 *            The new value for the user input text field.
	 * @pre. value is not null
	 */
	public static void setUserValue(String value)
	{
		userValueText.setText(value);
	}

	/**
	 * Set the history text field to a given String
	 * 
	 * @param String
	 *            to set text field to
	 */
	public static void setHistory(String value)
	{
		history.setText(value);
	}

	/**
	 * Set the string for the calculated value text field.
	 * 
	 * @param value
	 *            The new value for the calculated value text field.
	 * @pre. value is not null
	 */
	public static void setCalcValue(String value)
	{
		calcText.setText(value);
	}

	/**
	 * Set the expression value to specified Strin
	 * 
	 * @param value
	 *            The String to set to
	 */
	public static void setExpressionValue(String value)
	{
		expressionList.setText(value);
	}

	/**
	 * Removes text from user input field
	 */
	public static void clearUserValue()
	{
		if (userValueText.getText() != "")
		{
			userValueText.setText("");
		}
	}

	// Methods related to favourite list
	/*
	 * This method would get the selected favouite and set the expression to the
	 * selected one. This will in turn update the graph.
	 */
	

	//CURRENTLY BYPASSING
//	public static void setfavourite(int index) 
//	{
//		/*
//		 * Here this method will talk to the Graph and put the saved expression
//		 * on the graph. How it gets the information is done one of 2 ways: 
//		 * 	1) Have the graph be able to understand the expression from a String. 
//		 * 	2) Somehow store each expression in an Array and re-apply that old
//		 * 	   expression to the entire calculator 
//		 * #1 seems like a better way because it doesn't interfere with any of 
//		 * the stacks/rest of calculator. 
//		 * Going with #1:
//		 */
////		expressionList.setText((String) box.getSelectedItem());
////		CalcController theController = new CalcController();
////		theController.graphSelected((String) box.getSelectedItem());
//		
//	} CURRENTLY BYPASSING

	/*
	 * This method would save the current expression that the user has finished
	 * inputting.
	 */

	public static String findRoundingValue(String num)
	{
		
		String uV = num;
		int placeholder = uV.indexOf(".");
		
		//Checking to see how many digits to keep on the left hand side of the result
		//As well as how many digits on the right side to keep
		//Some rounding does still occur due to doubles.
		if(uV.contains("."))
		{			
			String rightDecimal = uV.substring(uV.indexOf("."), uV.length());
			int roundingLengthAfterDecimal = rightDecimal.length();
			if(rightDecimal.length() > roundingLengthAfterDecimal){
				//STILL NEED TO IMPLEMENT ROUNDING
				uV = uV.substring(0, placeholder) + uV.substring(placeholder, placeholder + 5);
				//System.out.println("Digits to the right " + rightDecimal.length());
			}

			String leftOfDecimal = uV.substring(0, placeholder);
			int roundingLengthBeforeDecimal = leftOfDecimal.length();
			if(leftOfDecimal.length() > roundingLengthBeforeDecimal){
				// roundingLengthBeforeDecimal = leftDecimal.length();
				//System.out.println("Digits to the left " + leftDecimal.length());
				if (uV.substring(1, uV.length()).length() > 6)
				{	
					uV = uV.substring(0, 1) + "." + uV.substring(1, 7) + "E" + uV.substring(1, uV.length() - 2).length();
				}
				System.out.println("it knows");
			}		
		}
		
		return uV;
	}
	
	/*
	 * Removes what is currently in the expression list from the selected menu.
	 * This is assuming that adding an expression to the list will set the expressionList 
	 * field that specific expression.
	 * I.e., the user will select from the list what they want to remove, then they will
	 * press delete and it will be removed. 
	 * */
	public static void deleting()
	{
		box.removeItem(expressionList.getText());
	}

}