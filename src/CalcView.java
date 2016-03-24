import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.*;


public class CalcView extends JFrame
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
	
	@SuppressWarnings("serial")
	public CalcView(final CalcController theController)
	{
		super("Simple Calculator");
		
		addComponentsToPane(this, theController);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@SuppressWarnings("serial")
	public static void addComponentsToPane(Container pane, final CalcController theController) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
	//natural height, maximum width
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
		
		
		int x = 0;
		int y = 3;
		final int k = 0;

		JButton button0 = new ButtonAdapter(""+0) {
			public void pressed() 
			{
				changeInputButton(0);
			}
		};
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button0, c);

		JButton button1 = new ButtonAdapter(""+1) {
			public void pressed() 
			{
				changeInputButton(1);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button1, c);

		JButton button2 = new ButtonAdapter(""+2) {
			public void pressed() 
			{
				changeInputButton(2);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button2, c);

		JButton button3 = new ButtonAdapter(""+3) {
			public void pressed() 
			{
				changeInputButton(3);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button3, c);

		JButton button4 = new ButtonAdapter(""+4) {
			public void pressed() 
			{
				changeInputButton(4);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 4;
		pane.add(button4, c);

		JButton button5 = new ButtonAdapter(""+5) {
			public void pressed() 
			{
				changeInputButton(5);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button5, c);

		JButton button6 = new ButtonAdapter(""+6) {
			public void pressed() 
			{
				changeInputButton(6);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button6, c);

		JButton button7 = new ButtonAdapter(""+7) {
			public void pressed() 
			{
				changeInputButton(7);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button7, c);

		JButton button8 = new ButtonAdapter(""+8) {
			public void pressed() 
			{
				changeInputButton(8);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button8, c);

		JButton button9 = new ButtonAdapter(""+9) {
			public void pressed() 
			{
				changeInputButton(9);
			}
		};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button9, c);
		
		button =  new ButtonAdapter("UNDO") {
			public void pressed(){
				registerButton("UNDO", theController);
			}
		};
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("+") {
			public void pressed(){
				registerButton("+", theController);
			}
		};
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("-") {
			public void pressed(){
				registerButton("-", theController);
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("×") {
			public void pressed(){
				registerButton("×", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("÷") {
			public void pressed(){
				registerButton("÷", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);

		button =  new ButtonAdapter("+/-") {
			public void pressed(){
				registerButton("+/-", theController);
			}
		};
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
		
		button =  new ButtonAdapter(".") {
			public void pressed(){
				registerButton(".", theController);
			}
		};
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
		
		button =  new ButtonAdapter("π") {
			public void pressed(){
				changeInputButton(Math.PI);
				
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);
		
		button =  new ButtonAdapter("sin") {
			public void pressed(){
				registerButton("sin", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);

		button =  new ButtonAdapter("cos") {
			public void pressed(){
				registerButton("cos", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 7;
		pane.add(button, c);

		button = new ButtonAdapter("!"){
			public void pressed(){
				registerButton("!", theController);
			}
		};
		
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		
		button = new ButtonAdapter("X"){
			public void pressed(){
				registerButton("X", theController);
			}
		};
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		
		button = new ButtonAdapter("TEST"){
			public void pressed(){
				registerButton("TEST", theController);
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		
		button = new ButtonAdapter("Graph"){
			public void pressed(){
				registerButton("Graph", theController);
				
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		
		button = new ButtonAdapter("INFO"){
			public void pressed(){
				registerButton("INFO", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 8;
		pane.add(button, c);
		
		
		
		y += 1;

		button = new ButtonAdapter("Enter") {public void pressed(){ addToHistory( theController );}};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 9;       //third row
		pane.add(button, c);
		
		button =  new ButtonAdapter("Clear") {public void pressed(){ theController.clear();}};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 3;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 9;       //third row
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
	 * @return false if the text field is empty
	 */
	public boolean containsUserValue(){
		if(userValueText.getText().equals("")){
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void registerButton(String button, CalcController theController) {		
		String his = history.getText();
		// right now this method is big, so when we refactor it we will put each button into its own controller method
		// furthermore, we will make the stack and history be part of the model
		
		if (button.equals("UNDO")){			
			System.out.println("UNDO");
			if(userValueText.getText().equals(""))
			{
				theController.undo();
			}
			else
			{
				String text = userValueText.getText();
				int length = userValueText.getText().length();
				text = text.substring(0, length-1);
				userValueText.setText(text);
			}
		}

		
		//If one of the following operator buttons is pushed, the appropriate
		//information is sent to the controller
		
		if (button.equals("+")) {
		//should call controller method addition
		//which calls model method of addition
			System.out.println("addition");			
			theController.sum();			
			userValueText.setText("");

		} else if (button.equals("-")) {
			System.out.println("subtract");			
			theController.subtract();			
			userValueText.setText("");

		} else if (button.equals("×")) {
			System.out.println("multiplty");			
			theController.multiply();			
			userValueText.setText("");
		} else if (button.equals("÷")) {
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
		// fixed negate button
		else if (button.equals("+/-")) {
			
			String userVal = userValueText.getText();
			char changeSign = userVal.charAt(0);
			if(!userVal.equals("0")){
				if (changeSign == '-') {
					userVal = userVal.substring(1, userVal.length());
					userValueText.setText(userVal);
				}else
				{
					userVal = '-'+userVal;
					userValueText.setText(userVal);
				}
			}

		} else if (button.equals(".")) {
			
			String userVal = userValueText.getText();
			
			
			if ( userVal.length() > 1 && userVal.charAt(1) == '.' ) {
				userVal = userVal.substring(1, his.length());
				userValueText.setText(userVal);
			} else {
				
				if (userVal.length() > 0 ) {
					userVal = userVal + ".";
				} else {
					userVal = "0." + userVal;
				}
				userValueText.setText(userVal);
			}
			
			
		} else if (button.equals("sin")) {
			
			System.out.print("Sin of ");
			
			theController.sin();
			
			userValueText.setText("");
		} 

		else if (button.equals("cos")) {
			
			System.out.print("Cos of ");
			
			theController.cos();
			
			userValueText.setText("");
		}

		else if (button.equals("!")){

			System.out.println("factorial");
			theController.factorial();
			userValueText.setText("");

		}
		else if (button.equals("X")){

			System.out.println("variable");
			theController.variable();
			userValueText.setText("");

		}

	}

	//This method to handle integers
	/**
	 * Enters the value of the button pressed into the text field
	 * @param buttonInput
	 */
	public static void changeInputButton(int buttonInput) {

		String value = String.valueOf(buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();
		
		if (history.getText().equals("Start a new calculation")) {
			history.setText("");
		}
		
		
	}
	
	//Added to handle doubles such as pi
	/**
	 * Enters the value of the button pressed into the text field
	 * @param buttonInput
	 */
	public static void changeInputButton(double buttonInput) {

		//Round to 5 digits
		String value = String.format("%.5f", buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();
		
		if (history.getText().equals("Start a new calculation")) {
			history.setText("");
		}
		
	}

	/**
	 * Sends the inputed number to the controller
	 * @param theController
	 */
	public static void addToHistory(CalcController theController) {
		String value = history.getText();
				
		double val = Double.parseDouble(userValueText.getText());
		
		System.out.println(""+val);
		
		BigDecimal allValue = new BigDecimal(val);
		theController.addToRounding(userValueText.getText());
		theController.addValue(allValue);

		userValueText.setText("");
		
	}
	
	public static void setButtonClicked() {
		buttonClicked = 0;
	}
	
	/**
	 * Set the string for the user input text field.
	 * 
	 * @param value The new value for the user input text field.
	 * @pre. value is not null
	 */
	public static void setUserValue(String value)
	{
		userValueText.setText(value);
	}
	
	/**
	 * Set the history text field to a given String
	 * @param String to set text field to
	 */
	public static void setHistory(String value) {
		history.setText(value);
	}
	
	/**
	 * Set the string for the calculated value text field.
	 * 
	 * @param value The new value for the calculated value text field.
	 * @pre. value is not null
	 */
	public static void setCalcValue(String value)
	{
		calcText.setText(value);
	}
	
	/**
	 * Set the expression value to specified Strin
	 * @param value The String to set to
	 */
	public static void setExpressionValue(String value){
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

	


}