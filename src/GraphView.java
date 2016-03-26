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

import org.jfree.chart.ChartPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GraphView extends JPanel implements KeyListener
{
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	static int buttonClicked = 0;

	public static GraphPanel panel2 = new GraphPanel(400,400);
	private static final long serialVersionUID = -8730447125113729547L;


import java.awt.Container;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;


public class GraphView extends JPanel{

	static JPanel graphPanel = new JPanel();
	
	public GraphView(final GraphController graphController){
		super();
		addComponentsToPane(this, graphController);
		this.setVisible(true);
		
	}

	public static void addComponentsToPane(Container pane, 
			final GraphController graphController)
	{
	
			//graphPanel.add(graphController.updateGraph());
			pane.add(graphPanel);
			pane.setVisible(true);
			
		System.out.println("Test");

		addComponentsToPane(this, theController);
//	  temp = new Graph();
//		
//	  requestFocus();

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
	public static void addComponentsToPane(Container pane,
			final GraphController theController)
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
		
		
		ChartPanel panel = theController.getChartPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		pane.add(panel, c);

		
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
			GraphController theController)
	{
		String his = history.getText();		

		

		if (button.equals("Graph")) {
			System.out.println("Create panel");
			//ChartPanel chPanel = new ChartPanel(chart); //creating the chart panel, which extends JPanel
			//chPanel.setPreferredSize(new Dimension(785, 440)); //size according to my window
			
			JPanel jPanel = new JPanel();

			jPanel.add(theController.getChartPanel()); //add the chart viewer to the JPanel

			JFrame newWindow = new JFrame();
			newWindow.setPreferredSize(new Dimension(500,500));
			newWindow.add(jPanel);
			newWindow.setVisible(true);
			newWindow.pack();
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

	public void updateGraph(ChartPanel graph){
		
		//Update chart

	}
	
}

