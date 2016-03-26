//import graphCalculator.Graph;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CalcModel 
{
	private BigDecimal calcValue;
	private Stack calculatedValues = new Stack<BigDecimal>();
	private Stack inputValues = new Stack();
	private Stack variables = new Stack();
	private Stack history;
	private Stack numbers;
	private Stack<String> expressionList = new Stack<String>();
	
	private Boolean containsVariable = false;
 
	//Amount to round to
	private MathContext roundingAmount = new MathContext(1);
	private MathContext roundingAmountResult = new MathContext(1);

	/**
	 * Creates a model with no user values and a calculated
	 * value of zero.
	 * 
	 */
	
    private int width, height;
    Graph temp;
    CalcView view;
    CalcController controller;

	public CalcModel()
	{
		//this.width = width;
		//this.height = height;
		//temp = new Graph();
		view = new CalcView(controller);
	   
		history = new Stack();
		numbers = new Stack();
		calcValue = calcValue.ZERO;	
	}
	
	public void setValue() {
		
	}
	/**
	 * Pushes the number entered from View into the stacks in model
	 * @param number
	 */
	public void pushNumber(BigDecimal number) {
		

		number = number.round(roundingAmount);
		expressionList.push(number.round(roundingAmount).toPlainString());
		numbers.push(number);
		inputValues.push(number);
		
		
	}

	/**
	 * Clears the user values and the calculated value.
	 */
	public void clear()
	{
		history.clear();
		numbers.clear();		
		expressionList.clear();
		calculatedValues.clear();
		inputValues.clear();
		
		containsVariable = false;
		
		roundingAmount = new MathContext(0);
		roundingAmountResult = new MathContext(0);
		
		calcValue = calcValue.ZERO;	
	}

	/**
	 * Adds the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to add to the current calculated value by.
	 */
	public void sum()
	{

		if(!containsVariable)
		{
		checkIfEnoughDigitsAvaliable(0);

		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.add(num1);
		
		addToExpressionList("+");

		numbers.push(calcValue);
		calculatedValues.push(calcValue);
		}
		else
		{
			addToExpressionList("+");
		}

	}
	
	/**
	 * Subtracts the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to subtract from the current calculated value by.
	 */
	public void subtract()
	{
		if(!containsVariable){
		checkIfEnoughDigitsAvaliable(0);
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.subtract(num1);
		
		addToExpressionList("-");
		
		numbers.push(calcValue);
		calculatedValues.push(calcValue);
		}
		else
		{
			addToExpressionList("-");
		}
	}
	
	/**
	 * Multiplies the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 */
	public void multiply()
	{
		if(!containsVariable){
		checkIfEnoughDigitsAvaliable(1);
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.multiply(num1);

		addToExpressionList("×");	//This is × for multiply, not x as in the letter!
		updateRounding(calcValue.toString());

		numbers.push(calcValue);
		calculatedValues.push(calcValue);
		}
		else
		{
			addToExpressionList("×");	
		}
	}
	
	/**
	 * Divides the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 * @pre. userValue is not equivalent to zero.
	 */
	public void divide()
	{
		

			if(!containsVariable){
				if(!(lastValue().equals(BigDecimal.ZERO)))
				{
					checkIfEnoughDigitsAvaliable(1);
					BigDecimal num1 = (BigDecimal) numbers.pop();
					System.out.println(num1);
					BigDecimal num2 = (BigDecimal) numbers.pop();
					System.out.println(num2);
					calcValue = num2.divide(num1, roundingAmount);
					addToExpressionList("÷");
					updateRounding(calcValue.toString());
					numbers.push(calcValue);
					calculatedValues.push(calcValue);
				}
			}
			else
			{
				addToExpressionList("÷");
			}
		

	}
	
	public void pi()
	{

		//Double pi = Math.PI;
		//BigDecimal num1 = BigDecimal.valueOf(pi);
		
		//num1 = num1.round(roundingAmount);
		
		//System.out.println("num1 " + num1.toPlainString());

		//addToExpressionList("π");

	}
	
	public void sin() {
		if(!containsVariable)
		{
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.sin(num1);
		System.out.println(num1);
		
		BigDecimal b = BigDecimal.valueOf(num1).round(roundingAmount);
		
		System.out.println("b is: " + b.toString());
		
		addToExpressionList("sin");
		numbers.push(b);
		calculatedValues.push(b);
		}
		else
		{
			addToExpressionList("sin");
		}
		
	}
	
	public void cos() {
		if(!containsVariable)
		{
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.cos(num1);
		System.out.println(num1);
		addToExpressionList("cos");
		BigDecimal b = BigDecimal.valueOf(num1);
		
		numbers.push(b);
		calculatedValues.push(b);
		}
		else
		{
			addToExpressionList("cos");
		}
		
	}
	
	public void factorial() {
		if(!containsVariable){
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = fact(num1);
		System.out.println(num1);

		addToExpressionList("!");
	
		BigDecimal b = BigDecimal.valueOf(num1).round(roundingAmount);

		
		numbers.push(b);
		calculatedValues.push(b);
		}
		else
		{
			addToExpressionList("!");
		}
	}
	
	public void variable(){
	
		containsVariable = true;
		addToExpressionList("X");

		
	}
	
	public void undo() {
		
		//System.out.println("-------BEFORE---------");
		//printAllStacks();


		if(isOperator(expressionList.peek()) || isTrignometric(expressionList.peek())|| isFactorial(expressionList.peek()) || isVariable(expressionList.peek())){

			expressionList.pop();
			if(calculatedValues.size()>0)
			{
			calculatedValues.pop();
			}
		}
		else
		{
			expressionList.pop();
			inputValues.pop();
		}
		numbers = (Stack) inputValues.clone();	
		//System.out.println("-------AFTER---------");
		//printAllStacks();

	}
	
	public static double fact(double b)
	{
		double r = 1.0;
		System.out.println("working with: " + b);
		while (b > 1.0)
		{
			r = r * b;
			b -= 1;
			System.out.println(r);
		}
		return r;
	}
	

	public BigDecimal lastValue(){
		if(numbers.size() > 0)
		{
			return (BigDecimal) numbers.peek();
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Get the current calculated value.
	 * 
	 * @return The current calculated value.
	 */
	public BigDecimal getCalcValue()
	{
		return (BigDecimal) numbers.peek();
	}
	
	/**
	 * Get last calculated Value, if there isn't one return 0
	 * @return Last calculated value
	 */
	public BigDecimal getCalculatedValue(){
		if(calculatedValues.size()>0){
		return ((BigDecimal) calculatedValues.peek()).round(roundingAmountResult);
		}
		else
		{
			return BigDecimal.valueOf(0);
		}
	}

	/**
	 * Push operator sign such as +,-,sin, etc. to expressionList stack.
	 * @param sign
	 */
	public void addToExpressionList(String sign){
		expressionList.push(sign);
	}
	
	/**
	 * Check if theres enough digits to do a calculation, if not input a filler number.
	 * For example (1,+) would give (1+0)
	 * @param number
	 */
	public void checkIfEnoughDigitsAvaliable(int number){
		//Way to handle situations such as the user entering 1,+;
		//Will automatically enter an appropriate value in place
		//so for example 1,+ becomes (1+0)
		if(numbers.size() == 1)
		{
			numbers.push(BigDecimal.valueOf(number));
			history.push(String.valueOf(number));
			expressionList.push(String.valueOf(number));
			inputValues.push(new BigDecimal(String.valueOf(number)));
		}
	}
	
	/**
	 * Check if a String is one of the following: +,-,x,÷,=.
	 * @param The String to check
	 * @return True or False
	 */
	public Boolean isOperator(String value){
		
		//Check to see if a string is an operator

		//Note that × is for multiply, not x as in the letter!
		if(value.equals("+") || value.equals("-")  || value.equals("×")|| value.equals("÷")|| value.equals("=")){	

			return true;
		}
		else
		{
			return false;
		}
		
	}
	/**
	 * Check if a String is one of the following: sin, cos.
	 * @param The String to check
	 * @return True or False
	 */
	public Boolean isTrignometric(String value){
		
		//Check to see if a string is a trig function
		if(value == "sin" || value == "cos"){
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public Boolean isFactorial(String value){
		
		//Check to see if a string is a factorial
		if(value == "!" ){
			return true;
		}
		else
		{
			return false;
		}
		
	}

	
	public Boolean isVariable(String value){
		
		//Check to see if a string is a variable
		if(value == "X" ){
			return true;
		}
		else
		{
			return false;
		}
		
	}
	/**
	 * Check if a given stack contains a String
	 * @param The stack to check in
	 * @param The String to search for
	 * @return True if the String is in the stack, false otherwise.
	 */
	public boolean stackContains(Stack stackIn,String value){
				
		Stack s = new Stack();
		s = (Stack) stackIn.clone();
		
		while(s.size() > 0){
			if(s.peek().toString() == value){
				return true;
			}
			else
			{
				s.pop();
			}
	
		}

		return false;
		
	}
	/**
	 * Convert postFix stack to inFix
	 * @return Converted String
	 */
	public String getExpressionValue(){
	
		String expression = "";
		
		//Create a clone of the expression list so we can pop things without disturbing the list
		/* 
		 * This is how this works....
		 * 
		 * We get the expressionList stack which could look like this:
		 * (Read the stacks right>left = top>bottom)
		 * 1,2,3,+,+
		 * We reverse it
		 * +,+,3,2,1 
		 * 
		 * We keep popping from this stack and putting the numbers into the container stack
		 * until we reach an operator like +,-,x,/
		 * Then we take the top two numbers from the container and put the operator 
		 * in between the two numbers and put it back into the container stack.
		 * We do this until we reach the end of the stack, then we reverse the
		 * container stack and print it out to a String.
		 */
		Stack cloneExpressionList = new Stack();
		Stack reverse = new Stack();
		Stack container = new Stack();

		cloneExpressionList = (Stack) expressionList.clone();
		
		while(cloneExpressionList.size() > 0){
			reverse.push(cloneExpressionList.pop());
		}
		
		while(reverse.size()>0){
			
			String value = reverse.pop().toString();
			
			//Check is +,-,/,x
			if(isOperator(value)){
				
				String number2 = container.pop().toString();
				String number1 = container.pop().toString();
				
				container.push("(" + number1 + value + number2 + ")");
				
			}
			//Check if sin or cos
			else if(isTrignometric(value))
			{
				String number1 = container.pop().toString();
				
				container.push("(" + value + "(" + number1 + "))");
			}
			else if (isFactorial(value))
			{
				String number1 = container.pop().toString();
				container.push("("+number1 + value+")");
			}
			else if(isVariable(value))
			{
				//String number1 = container.pop().toString();
				
				container.push( value );
			}
			else
			{
				container.push(value);
			}

		}
		
		//System.out.println("Container stack: ");
		//printStackToConsole(container);
		
		//Reverse the container stack
		while(container.size() > 0){
			reverse.push(container.pop());
		}

		//System.out.println("Container stack: ");
		//printStackToConsole(reverse);
		
		//Add everything in the container to a String separated by commas
		while(reverse.size()>0){
			expression = expression + "," + reverse.pop();
		}
		
		//Shave off the excess comma at the begining
		if(expression.length() > 0)
		{
		expression = expression.substring(1, expression.length());
		}
		return expression;
	}
	
	/**
	 * Get the history of inputed numbers, seperated by commas
	 * @return A string of numbers inputed
	 */
	public String getNumberHistory(){
		
		String returnValue = "";
		
		//System.out.println("numbers size: " + numbers.size());
		//System.out.println("expression list size: " + expressionList.size());
		
		for(int i = 0; i < expressionList.size(); i++)
		{
			String expressionValue = expressionList.get(i);
			if(!isOperator(expressionValue) && !isTrignometric(expressionValue))
			{
				returnValue = returnValue  + expressionValue.toString()+ ",";
			}
		}
		
		if(returnValue.length() > 0)
		{
		returnValue = returnValue.substring(0, returnValue.length()-1);
		}
		
		//System.out.println("History: " + returnValue);
		
		return returnValue;
	}
	
	public String getEquation(){
		if(containsVariable){
			return getExpressionValue();
		}
		else
		{
			if(calculatedValues.size() > 0){
			return calculatedValues.peek().toString();
			}
			else
			{
				return "0";
			}
		}
	}
	
	/**
	 * Get all inputs seperated by commas as a String
	 * @return String History of all inputs
	 */
	public String getHistory(){
		
		String returnValue = "";
		
		//System.out.println("numbers size: " + numbers.size());
		//System.out.println("expression list size: " + expressionList.size());
		
		for(int i = 0; i < expressionList.size(); i++)
		{
			String expressionValue = expressionList.get(i);

				returnValue = returnValue  + expressionValue.toString()+ ",";
			
		}
		
		if(returnValue.length() > 0)
		{
		returnValue = returnValue.substring(0, returnValue.length()-1);
		}
		
		//System.out.println("History: " + returnValue);
		
		return returnValue;
	}
	
	/**
	 * Analyzes a string to determine how many decimal places to round to
	 * @param String number as a string to analyze
	 */
	public void updateRounding(String value){
		
		int precision = value.length();

		if(value.contains(".")){
			
			precision-=1;
		}
		
		roundingAmount = new MathContext(precision);
		System.out.println("Rounding input to " + roundingAmount.getPrecision() + " decimal places");
		
		if(precision > roundingAmountResult.getPrecision()){
			roundingAmountResult = new MathContext(precision + 1);
			System.out.println("Now rounding final result to " + roundingAmount.getPrecision() + " decimal places");
		}
		
	}
	
	/**
	 * Print a stack to console from top to bottom.
	 * @param The stack to print
	 */
	public void printStackToConsole(Stack stackToPrint){
		
		Stack clone = new Stack();
		
		clone = (Stack) stackToPrint.clone();
		
		while(clone.size() > 0)
		{
			System.out.println(clone.pop().toString());
		}
		
	}
	
	/**
	 * Prints all stacks in model to console.
	 */
	public void printAllStacks(){
		System.out.println("-----------------");
		System.out.println("Expression List Stack: ");
		printStackToConsole(expressionList);
		System.out.println("Input Values Stack: ");
		printStackToConsole(inputValues);
		System.out.println("Numbers Stack: ");
		printStackToConsole(numbers);
		System.out.println("Calculated Values Stack: ");
		printStackToConsole(calculatedValues);
		System.out.println("-----------------");
	}
	

	/**
	 * Prints the current input as a test case to console
	 */
	public void printAsTestCase(){
		
		System.out.println("Printing Test Case Info");
		
		String expression = "{";
		for(int i = 0; i < expressionList.size(); i++){
			expression = expression + " \"" + expressionList.get(i) + "\"" + ",";
		}
		expression = "String[] expressionArray = " + expression.substring(0,expression.length()-1) +"};";
		System.out.println(expression);
		
		String input = "{";
		for(int i = 0; i < inputValues.size(); i++){
			input = input + " \"" + inputValues.get(i) + "\"" + ",";
		}
		input = "String[] inputValuesArray = " + input.substring(0,input.length()-1) +"};";
		System.out.println(input);
		
		String num = "{";
		for(int i = 0; i < numbers.size(); i++){
			num = num + " \"" + numbers.get(i) + "\"" + ",";
		}
		num = "String[] numbersArray = " + num.substring(0,num.length()-1) +"};";
		System.out.println(num);
		
		String calcVal = "{";
		for(int i = 0; i < calculatedValues.size(); i++){
			calcVal = calcVal + " \"" + calculatedValues.get(i) + "\"" + ",";
		}
		calcVal = "String[] calculatedValuesArray = " + calcVal.substring(0,calcVal.length()-1) +"};";
		System.out.println(calcVal);
		
		System.out.println("-----------------");
		
	}
	
	/**
	 * Simulates the generated input for specific values
	 * Use the INFO button to print the test cases for easy testing
	 */
	public void getTestCase()
	{

		clear();
		
		//Add test case in infix notation
		//You can use the INFO Button to generate this list easily
		//and just copy and paste from console to here.
		String[] expressionArray = { "21", "35", "×", "101", "4", "×", "+"};
		String[] inputValuesArray = { "21", "35", "101", "4"};
		String[] numbersArray = { "1139"};
		String[] calculatedValuesArray = { "735", "404", "1139"};
		
		generateTestCase(expressionArray,inputValuesArray,numbersArray,calculatedValuesArray);
		
	}
	
	/**
	 * Generates a simulated input for the calculator, for testing purposes
	 * 
	 * @param expressionArray
	 * @param inputValuesArray
	 * @param numbersArray
	 * @param calculatedValuesArray
	 */
	public void generateTestCase(String[] expressionArray, String[] inputValuesArray, String[] numbersArray, String[] calculatedValuesArray){
		
		
		for(int i = 0; i < expressionArray.length; i++){
			String index = expressionArray[i];
			expressionList.push(index);
		}
		
		for(int i = 0; i < inputValuesArray.length; i++){
			String index = inputValuesArray[i];
			BigDecimal value = new BigDecimal(index);
			inputValues.push(value);
			
		}
		
		for(int i = 0; i < numbersArray.length; i++){
			String index = numbersArray[i];
			BigDecimal value = new BigDecimal(index);
			numbers.push(value);
			
		}
		
		for(int i = 0; i < calculatedValuesArray.length; i++){
			String index = calculatedValuesArray[i];
			BigDecimal value = new BigDecimal(index);
			calculatedValues.push(value);
			
		}
		
	}
	
	
	
	
	
	
	
}