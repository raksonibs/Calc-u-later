//import graphCalculator.Graph;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.IOException;
import java.io.InputStream;
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
	    ImageIcon grayIconSmall = createImageIcon("/ButtonIcons/SmallIconG.png");
	    ImageIcon orangeIconSmall = createImageIcon("/ButtonIcons/SmallIconO.png");
	    ImageIcon blueIconWide = createImageIcon("/ButtonIcons/WideIconB.png");
	    ImageIcon greenIconWide = createImageIcon("/ButtonIcons/WideIconGreen.png");
	    ImageIcon yellowIconSmall = createImageIcon("/ButtonIcons/SmallIconY.png");
	    ImageIcon redIconSmall = createImageIcon("/ButtonIcons/SmallIconR.png");
	    ImageIcon blueIconSmall = createImageIcon("/ButtonIcons/SmallIconB.png");
	    //ImageIcon background = createImageIcon("/ButtonIcons/background.png");
	    
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

		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);
	    button.setIcon(yellowIconSmall);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		button = new ButtonAdapter("π")
		{
			public void pressed()
			{
				changeInputButton(Math.PI);

			}
		};
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);
		
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 7;
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
		c.gridy = 8;
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		//****************


		button = new ButtonAdapter("sin")
		{
			public void pressed()
			{
				registerButton("sin", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);

		button = new ButtonAdapter("cos")
		{
			public void pressed()
			{
				registerButton("cos", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);

		button = new ButtonAdapter("INFO")
		{
			public void pressed()
			{
				registerButton("INFO", theController);
			}
		};

		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);	
		pane.add(button, c);
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);

		// Adding SAVE and DELETE buttons related to favourites list.
		button = new ButtonAdapter("SAVE")
		{
			public void pressed()
			{
				registerButton("SAVE", theController);
			}
		};

		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);	
	    button.setIcon(blueIconSmall);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		

		button =  new ButtonAdapter("Clear") {public void pressed(){ theController.clear();}};		
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);	
	    button.setIcon(redIconSmall);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		
		JButton button0 = new ButtonAdapter("" + 0)
		{
			public void pressed()
			{
				changeInputButton(0);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button0, c);
	    button0.setIcon(grayIconSmall);
	    button0.setPreferredSize(new Dimension(70,30));
	    button0.setHorizontalTextPosition(button0.CENTER);
		button0.setOpaque(true);
		button0.setBorderPainted(false);
		

		JButton button1 = new ButtonAdapter("" + 1)
		{
			
			public void pressed()
			{
				changeInputButton(1);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button1, c);
	    button1.setIcon(grayIconSmall);
	    button1.setPreferredSize(new Dimension(70,30));
	    button1.setHorizontalTextPosition(button1.CENTER);
		button1.setOpaque(true);
		button1.setBorderPainted(false);

		JButton button2 = new ButtonAdapter("" + 2)
		{
			public void pressed()
			{
				changeInputButton(2);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button2, c);
	    button2.setIcon(grayIconSmall);
	    button2.setPreferredSize(new Dimension(70,30));
	    button2.setHorizontalTextPosition(button2.CENTER);
		button2.setOpaque(true);
		button2.setBorderPainted(false);


		JButton button3 = new ButtonAdapter("" + 3)
		{
			public void pressed()
			{
				changeInputButton(3);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button3, c);
	    button3.setIcon(grayIconSmall);
	    button3.setPreferredSize(new Dimension(70,30));
	    button3.setHorizontalTextPosition(button3.CENTER);
		button3.setOpaque(true);
		button3.setBorderPainted(false);
		
		JButton button4 = new ButtonAdapter("" + 4)
		{
			public void pressed()
			{
				changeInputButton(4);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button4, c);
	    button4.setIcon(grayIconSmall);
	    button4.setPreferredSize(new Dimension(70,30));
	    button4.setHorizontalTextPosition(button4.CENTER);
		button4.setOpaque(true);
		button4.setBorderPainted(false);
		
		JButton button5 = new ButtonAdapter("" + 5)
		{
			public void pressed()
			{
				changeInputButton(5);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button5, c);
	    button5.setIcon(grayIconSmall);
	    button5.setPreferredSize(new Dimension(70,30));
	    button5.setHorizontalTextPosition(button5.CENTER);
		button5.setOpaque(true);
		button5.setBorderPainted(false);
		
		JButton button6 = new ButtonAdapter("" + 6)
		{
			public void pressed()
			{
				changeInputButton(6);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button6, c);
	    button6.setIcon(grayIconSmall);
	    button6.setPreferredSize(new Dimension(70,30));
	    button6.setHorizontalTextPosition(button6.CENTER);
		button6.setOpaque(true);
		button6.setBorderPainted(false);
		
		JButton button7 = new ButtonAdapter("" + 7)
		{
			public void pressed()
			{
				changeInputButton(7);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button7, c);
	    button7.setIcon(grayIconSmall);
	    button7.setPreferredSize(new Dimension(70,30));
	    button7.setHorizontalTextPosition(button7.CENTER);
		button7.setOpaque(true);
		button7.setBorderPainted(false);
		
		JButton button8 = new ButtonAdapter("" + 8)
		{
			public void pressed()
			{
				changeInputButton(8);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button8, c);
	    button8.setIcon(grayIconSmall);
	    button8.setPreferredSize(new Dimension(70,30));
	    button8.setHorizontalTextPosition(button8.CENTER);
		button8.setOpaque(true);
		button8.setBorderPainted(false);
		
		JButton button9 = new ButtonAdapter("" + 9)
		{
			public void pressed()
			{
				changeInputButton(9);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button9, c);
	    button9.setIcon(grayIconSmall);
	    button9.setPreferredSize(new Dimension(70,30));
	    button9.setHorizontalTextPosition(button9.CENTER);
		button9.setOpaque(true);
		button9.setBorderPainted(false);
		
		button = new ButtonAdapter("+")
		{
			public void pressed()
			{
				registerButton("+", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		button = new ButtonAdapter("-")
		{
			public void pressed()
			{
				registerButton("-", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		button = new ButtonAdapter("x")
		{
			public void pressed()
			{
				registerButton("*", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		button = new ButtonAdapter("/")
		{
			public void pressed()
			{
				registerButton("/", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		button = new ButtonAdapter("+/-")
		{
			public void pressed()
			{
				registerButton("+/-", theController);
			}
		};

		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);

		button = new ButtonAdapter(".")
		{
			public void pressed()
			{
				registerButton(".", theController);
			}
		};

		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
		pane.add(button, c);
	    button.setIcon(orangeIconSmall);
	    button.setPreferredSize(new Dimension(70,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		button = new ButtonAdapter("X"){
			public void pressed(){
				registerButton("X", theController);
			}
		};

		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button, c);
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);
		
		button = new ButtonAdapter("Graph"){
			public void pressed() {
				registerButton("Graph", theController);
				MAIN.setSelectedIndex(1);
			}
		};

		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 9;
		pane.add(button, c);
	    button.setIcon(blueIconSmall);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		button = new ButtonAdapter("Test"){
			public void pressed(){
				registerButton("TEST", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		pane.add(button, c);
	    button.setIcon(blueIconWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		//button.setForeground(Color.white);

		button = new ButtonAdapter("Enter") {public void pressed(){ addToHistory( theController );}};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 3;   //2 columns wide
		c.gridy = 8;       //third row
		pane.add(button, c);
	    button.setIcon(greenIconWide);
	    button.setPreferredSize(new Dimension(130,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
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
		else if (button.equals("*"))
		{
			System.out.println("multiplty");
			theController.multiply();
			userValueText.setText("");
		}
		else if (button.equals("/"))
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
			String s = expressionList.getText();
			favC.saving(s);
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
				
				userVal = userVal.substring(2, userVal.length());
				
				userValueText.setText(userVal);
			}
			else
			{

				if (userVal.length() > 0)
				{
					if ((userVal.length() - userVal.replace(".", "").length()) > 0) {
						// more than one decimal being inputted, don't allow
					} else {
						userVal = userVal + ".";
					}
					
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

	public String getExpressionValue(){
		return expressionList.getText();
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