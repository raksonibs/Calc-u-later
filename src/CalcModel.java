import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class CalcModel
{
	private BigDecimal calcValue;
	private Stack calculatedValues = new Stack();
	private Stack inputValues = new Stack();
	private Stack history;
	private Stack numbers;
	private Stack<String> expressionList = new Stack<String>();

	//Amount to round to
	private MathContext roundingAmount = new MathContext(10);

	/**
	 * Creates a model with no user values and a calculated
	 * value of zero.
	 * 
	 */
	public CalcModel()
	{
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
		
		System.out.println("numbers size: " + numbers.size());
		System.out.println("expression list size: " + expressionList.size());

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
	
	/**
	 * Subtracts the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to subtract from the current calculated value by.
	 */
	public void subtract()
	{
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
	
	/**
	 * Multiplies the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 */
	public void multiply()
	{
		checkIfEnoughDigitsAvaliable(1);
		
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.multiply(num1);
		
		addToExpressionList("x");
		
		numbers.push(calcValue);
		calculatedValues.push(calcValue);
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
		//The number of digits we want to round divisons to

		checkIfEnoughDigitsAvaliable(1);
		
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.divide(num1, roundingAmount);
		
		//System.out.println("Division Value is: " + calcValue.toPlainString());
		
		addToExpressionList("÷");
				
		numbers.push(calcValue);
		calculatedValues.push(calcValue);
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
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.sin(num1);
		System.out.println(num1);
		
		BigDecimal b = BigDecimal.valueOf(num1).round(roundingAmount);
		//b = b.round(roundingAmount);
		
		System.out.println("b is: " + b.toString());
		
		addToExpressionList("sin");
		numbers.push(b);
	}
	
	public void cos() {
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.cos(num1);
		System.out.println(num1);
		addToExpressionList("cos");
		BigDecimal b = BigDecimal.valueOf(num1);
		
		numbers.push(b);
	}
	
	public void factorial() {
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = fact(num1);
		System.out.println(num1);
	
		BigDecimal b = BigDecimal.valueOf(num1);
		
		numbers.push(b);
	}
	
	public void undo() {
		
		//System.out.println("-------BEFORE---------");
		//printAllStacks();

		if(isOperator(expressionList.peek())){
			expressionList.pop();
			calculatedValues.pop();
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
		return (BigDecimal) numbers.peek();
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
	
	public BigDecimal getCalculatedValue(){
		if(calculatedValues.size()>0){
		return (BigDecimal) calculatedValues.peek();
		}
		else
		{
			return BigDecimal.valueOf(0);
		}
	}

	public void addToExpressionList(String sign){
		expressionList.push(sign);
	}
	
	public void checkIfEnoughDigitsAvaliable(int number){
		//Way to handle situations such as the user entering 1,+;
		//Will automatically enter an appropriate value in place
		//so for example 1,+ becomes (1+0)
		if(numbers.size() == 1)
		{
			numbers.push(BigDecimal.valueOf(number));
			history.push(String.valueOf(number));
			expressionList.push(String.valueOf(number));
		}
	}
	
	public Boolean isOperator(String value){
		
		//Check to see if a string is an operator
		if(value == "+" || value == "-" || value == "x"|| value == "÷"|| value == "="){
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
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
	
	public String getExpressionValue()
	{
		
		//System.out.println("expression list top: " + expressionList.peek());
		//System.out.println("infix notation list: " + inFixNotationList.peek());
		
		String returnValue = "";
		String expression = "";
		//Create a clone of the expression list so we can pop things without disturbing the list
		Stack s = new Stack();
		s = (Stack) expressionList.clone();
		
		//System.out.println("numbers size: " + numbers.size());
		//System.out.println("expression list size: " + expressionList.size());
		
		if(stackContains(s,"+") || stackContains(s,"-" ) || stackContains(s,"÷") || stackContains(s,"x")){
		
			for(int i = 0; i < expressionList.size(); i++)
			{
				String expressionValue = expressionList.get(i);
				//If we reach an operator in the stack then we pop the two previous numbers and insert the 
				//operator in between them, as well as add brackets around the equation.
				
				if(isOperator(expressionValue)){
					String number2 = s.pop().toString();
					String number1 = s.pop().toString();
					
					s.push("(" + number1 + expressionValue + number2 + ")");
				}
				//If we reach a trignometric operation we only want to pop one number and add brackets
				else if(isTrignometric(expressionValue))
				{
					String number1 = s.pop().toString();
					s.push("(" + expressionValue + "(" + number1 + "))");
					
				}
				else
				{
					s.push(""+expressionValue);
				}
		
			}
			
			expression = s.pop().toString();
			//Shave off the brackets on the end and add an equals sign
			if(expression.length() > 1){
			expression = expression.substring(1, expression.length()-1) + "=";
			}
			
			return expression;
		}
		else
		{
			
			Stack reverse = new Stack();
			
			while(s.size() > 0){
				reverse.push(s.pop());
			}
			
			while(reverse.size()>0){
				returnValue = returnValue + reverse.pop() + ",";
			}
			
			return returnValue.substring(0,returnValue.length()-1);
		}

	}
	
	public String getHistory(){
		
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
		
		returnValue = returnValue.substring(0, returnValue.length()-1);
		System.out.println("History: " + returnValue);
		
		return returnValue;
	}
	
	public void printStackToConsole(Stack stackToPrint){
		
		Stack clone = new Stack();
		
		clone = (Stack) stackToPrint.clone();
		
		while(clone.size() > 0)
		{
			System.out.println(clone.pop().toString());
		}
		
	}
	
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
	
	
	
	
	
	
	
	
	
	
}