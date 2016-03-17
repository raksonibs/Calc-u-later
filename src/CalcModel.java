import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalcModel
{
	private BigDecimal calcValue;
	private Stack history;
	private Stack numbers;
	private Stack<String> expressionList = new Stack<String>();
	private Stack<String> inFixNotationList = new Stack<String>();
	
	private String userInput = "";

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
	
	public void pushNumber(BigDecimal number) {
		numbers.push(number);

	}
	public void pushExpression(String valueIn) {
		expressionList.push(valueIn);
	}

	/**
	 * Clears the user values and the calculated value.
	 */
	public void clear()
	{
		history.clear();
		numbers.clear();		
		expressionList.clear();
		
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
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.add(num1);
		
		if(expressionList.size() == 0)
		{
			
			Stack temporary = new Stack();
			
			while(inFixNotationList.size() > 0)
			{
				temporary.push(inFixNotationList.pop());
			}
			
			inFixNotationList.push("(" + temporary.pop() + ")" + "+" + "(" + temporary.pop() + ")");
		}
		else
		{
			inFixNotationList.push(expressionList.pop() + "+" + expressionList.pop());	
		}

		
		numbers.push(calcValue);
	}
	
	/**
	 * Subtracts the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to subtract from the current calculated value by.
	 */
	public void subtract()
	{
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.subtract(num1);
		
		if(expressionList.size() == 0)
		{
			
			Stack temporary = new Stack();
			
			while(inFixNotationList.size() > 0)
			{
				temporary.push(inFixNotationList.pop());
			}
			
			inFixNotationList.push("(" + temporary.pop() + ")" + "-" + "(" + temporary.pop() + ")");
		}
		else
		{
			inFixNotationList.push(expressionList.pop() + "-" + expressionList.pop());	
		}
		
		numbers.push(calcValue);
	}
	
	/**
	 * Multiplies the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 */
	public void multiply()
	{
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.multiply(num1);
		
		if(expressionList.size() == 0)
		{
			
			Stack temporary = new Stack();
			
			while(inFixNotationList.size() > 0)
			{
				temporary.push(inFixNotationList.pop());
			}
			
			inFixNotationList.push("(" + temporary.pop() + ")" + "x" + "(" + temporary.pop() + ")");
		}
		else
		{
			inFixNotationList.push(expressionList.pop() + "x" + expressionList.pop());	
		}
		
		numbers.push(calcValue);
	}
	
	public void sin() {
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.sin(num1);
		System.out.println(num1);
		
		BigDecimal b = BigDecimal.valueOf(num1);
		
		numbers.push(b);
	}
	
	public void cos() {
		Double num1 = ((BigDecimal) numbers.pop()).doubleValue();
		System.out.println(num1);
		num1 = Math.cos(num1);
		System.out.println(num1);
		
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
		numbers.pop();
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
	
	/**
	 * Divides the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 * @pre. userValue is not equivalent to zero.
	 */
	public void divide()
	{
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.divide(num1);
		
		if(expressionList.size() == 0)
		{
			
			Stack temporary = new Stack();
			
			while(inFixNotationList.size() > 0)
			{
				temporary.push(inFixNotationList.pop());
			}
			
			inFixNotationList.push("(" + temporary.pop() + ")" + "÷" + "(" + temporary.pop() + ")");
		}
		else
		{
			inFixNotationList.push(expressionList.pop() + "÷" + expressionList.pop());	
		}
		
		numbers.push(calcValue);
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
	
	public String getExpressionValue()
	{
		
		//System.out.println("expression list: " + expressionList.peek());
		System.out.println("infix notation list: " + inFixNotationList.peek());
		
		Stack temporary = new Stack();
		String returnValue = "";
		
		temporary = (Stack) inFixNotationList.clone();
		
		while(temporary.size() > 0){
			
			if(temporary.size() == 1){
				returnValue = returnValue + temporary.pop() + "=";	
			}else{
				returnValue = returnValue + temporary.pop() + ",";
			}
				
		}
		


		return returnValue;
	}
	
	public String updateUserInput(String value){
		
		userInput = userInput + value;
		return userInput;
		
	}
	
}
