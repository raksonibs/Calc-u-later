import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	private static Stack<BigDecimal> numbers;
	
	@SuppressWarnings("serial")
	public CalcView(final CalcController theController)
	{
		super("Simple Calculator");
		
		addComponentsToPane(this, theController);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.numbers = new Stack();
		
	}

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
		pane.add(userValueText, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
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
		c.gridy = 3;
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
		c.gridy = 3;
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
		c.gridy = 3;
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
		c.gridy = 3;
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
		c.gridy = 3;
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
		c.gridy = 4;
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
		c.gridy = 4;
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
		c.gridy = 4;
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
		c.gridy = 4;
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
		c.gridy = 4;
		pane.add(button9, c);
		
		button = new ButtonAdapter(".") {public void pressed(){ theController.clear();}};
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
		
		button =  new ButtonAdapter("+") {
			public void pressed(){
				registerButton("+", theController);
			}
		};
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
		
		button =  new ButtonAdapter("-") {
			public void pressed(){
				registerButton("-", theController);
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
		
		button =  new ButtonAdapter("*") {
			public void pressed(){
				registerButton("*", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);
		
		button =  new ButtonAdapter("/") {
			public void pressed(){
				registerButton("/", theController);
			}
		};
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 5;
		pane.add(button, c);

		button =  new ButtonAdapter("+/-") {
			public void pressed(){
				registerButton("+/-", theController);
			}
		};
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter(".") {
			public void pressed(){
				registerButton(".", theController);
			}
		};
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("π") {
			public void pressed(){
				changeInputButton(Math.PI);	//So basically 3?
			}
		};
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("sin") {
			public void pressed(){
				registerButton("sin", theController);
			}
		};
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 6;
		pane.add(button, c);
		
		button =  new ButtonAdapter("!") {
			public void pressed(){
				registerButton("!", theController);
			}
		};
		c.gridx = 0;
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
		c.gridy = 6;
		pane.add(button, c);
		
		y += 1;

		button = new ButtonAdapter("Enter") {public void pressed(){ addToHistory();}};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 7;       //third row
		pane.add(button, c);
		
		button =  new ButtonAdapter("Clear") {public void pressed(){ theController.clear();}};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 3;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 7;       //third row
		pane.add(button, c);
}

	/**
	 * Get the string value of the user input text field.
	 * 
	 * @return The string in the user input text field.
	 */
	public static BigInteger getUserValue()
	{
		return new BigInteger(userValueText.getText());
	}
	
	public static void registerButton(String button, CalcController theController) {		
		String his = history.getText();
		// right now this method is big, so when we refactor it we will put each button into its own controller method
		// furthermore, we will make the stack and history be part of the model
		if (!button.equals("+/-") && !button.equals(".")) {
			char lastChar = his.charAt(his.length() - 1);
			if (lastChar == '=') {
				String removeEquals = his.substring(0, his.length() - 1);
				history.setText(removeEquals);
			}
		}
		
		
		if (button.equals("+")) {
			System.out.println("addition");
			String input = userValueText.getText();
			
			
			if (!userValueText.getText().equals("")) {
				// push number only if value inputted
				double val = Double.valueOf(userValueText.getText());
				numbers.push(new BigDecimal(val));
			}
			history.setText(his+","+input+button+"=");

			BigDecimal num1 = numbers.pop();
			System.out.println(num1);
			BigDecimal num2 = numbers.pop();
			System.out.println(num2);
			BigDecimal value = num2.add(num1);
			numbers.push(value);
			
			setCalcValue(value.toString());
			
			userValueText.setText("");
		} else if (button.equals("-")) {
			System.out.println("subtracting");
			String input = userValueText.getText();
			
			if (!userValueText.getText().equals("")) {
				// push number only if value inputted
				double val = Double.parseDouble(userValueText.getText());
				numbers.push(BigDecimal.valueOf(val));
			}

			history.setText(his+","+input+button+"=");

			BigDecimal num1 = numbers.pop();
			System.out.println(num1);
			BigDecimal num2 = numbers.pop();
			System.out.println(num2);
			BigDecimal value = num2.subtract(num1);
			numbers.push(value);
			
			setCalcValue(value.toString());
			
			userValueText.setText("");
			
			userValueText.setText("");
		} else if (button.equals("*")) {
			System.out.println("multiplying");
			String input = userValueText.getText();
			if (!userValueText.getText().equals("")) {
				// push number only if value inputted
				int val = Integer.parseInt(userValueText.getText());
				numbers.push(BigDecimal.valueOf(val));
			}
			
			history.setText(his+","+input+button+"=");
	
			BigDecimal num1 = numbers.pop();
			System.out.println(num1);
			BigDecimal num2 = numbers.pop();
			System.out.println(num2);
			BigDecimal value = num2.multiply(num1);
			numbers.push(value);
			
			setCalcValue(value.toString());
			
			userValueText.setText("");

		} else if (button.equals("/")) {
			System.out.println("dividng");
			String s = userValueText.getText();
			if (!userValueText.getText().equals("")) {
				// push number only if value inputted
				double val = Double.parseDouble(userValueText.getText());
				numbers.push(BigDecimal.valueOf(val));
			}
			
			history.setText(his+","+s+button+"=");
			
			BigDecimal num1 = numbers.pop();
			System.out.println(num1);
			BigDecimal num2 = numbers.pop();
			System.out.println(num2);
			BigDecimal value = num2.divide(num1);
			numbers.push(value);

			if (num1 == BigDecimal.ZERO){
				setCalcValue("YOU JUST DIVIDED BY ZERO");
				throw new IllegalArgumentException("I can't believe you've done this.");
			}

			setCalcValue(value.toString());
			userValueText.setText("");

			}	

		else if (button.equals("+/-")) {
			
			String userVal = userValueText.getText();
			char changeSign = userVal.charAt(0);
			
			if (changeSign == '-') {
				userVal = userVal.substring(1, his.length());
				userValueText.setText(userVal);
			} else {
				userVal = "-"+userVal;
				userValueText.setText(userVal);
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
			
			//THIS WILL ALWAYS BE 0 UNTIL DECIMALS ARE SORTED OUT!
			System.out.print("Sin of ");
			String input = userValueText.getText();

			history.setText(his+","+input+button+"=");
	
			//Fixed convolution
			Double num1 = numbers.pop().doubleValue();
			System.out.println(num1);
			num1 = Math.sin(num1);
			System.out.println(num1);
			
			BigDecimal b = BigDecimal.valueOf(num1);

			numbers.push(b);
			setCalcValue(num1.toString());
			userValueText.setText("");
		} 

		else if (button.equals("cos")) {
			
			//THIS WILL ALWAYS BE 0 UNTIL DECIMALS ARE SORTED OUT!
			System.out.print("Cos of ");
			String input = userValueText.getText();

			history.setText(his+","+input+button+"=");
	
			//Fixed convolution
			Double num1 = numbers.pop().doubleValue();
			System.out.println(num1);
			num1 = Math.sin(num1);
			System.out.println(num1);
			
			BigDecimal b = BigDecimal.valueOf(num1);

			numbers.push(b);
			setCalcValue(num1.toString());
			userValueText.setText("");
			
		}else if (button.equals("!")){
			
			System.out.println("Factorial");
			String input  = userValueText.getText();
			double d = Double.valueOf(numbers.pop().toString());
			
			history.setText(his+","+input+button+"=");
			Double result = factorial(d);
			BigDecimal ans = new BigDecimal(result);
			
			numbers.push(ans);
			setCalcValue(result.toString());
			userValueText.setText("");
			
			
		}

	}
	public static double factorial(double num){
		if(num == 1)
			return 1;
		else
			return num * factorial(num-1);
	}
	
	public static void changeInputButton(int buttonInput) {

		String value = String.valueOf(buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();
		
		if (history.getText().equals("Start a new calculation")) {
			history.setText("");
		}
		
		char lastChar = his.charAt(his.length() - 1);
		if (lastChar == '=') {
			String removeEquals = his.substring(0, his.length() - 1);
			history.setText(removeEquals);
		}
	}
	
	//Added to handle doubles such as pi
	public static void changeInputButton(double buttonInput) {
		String value = String.valueOf(buttonInput);
		value = userValueText.getText() + value;
		userValueText.setText(value);
		String his = history.getText();
		
		if (history.getText().equals("Start a new calculation")) {
			history.setText("");
		}
		
		char lastChar = his.charAt(his.length() - 1);
		if (lastChar == '=') {
			String removeEquals = his.substring(0, his.length() - 1);
			history.setText(removeEquals);
		}

	}
	

	public static void addToHistory() {
		String value = history.getText();
		
		double val = Double.parseDouble(userValueText.getText());
		
		System.out.println(""+val);
		
		BigDecimal allValue = new BigDecimal(val);
		numbers.push(allValue);
		System.out.println(numbers.get(numbers.size() -1));
		
		System.out.println("over here");
				
		if (buttonClicked == 0) {
			buttonClicked += 1;
		} else {
			buttonClicked += 1;
			value += ",";
		}
		
		value += userValueText.getText();		
		
		history.setText(value);
		
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

}