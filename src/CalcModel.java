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

	/**
	 * Creates a model with no user values and a calculated
	 * value of zero.
	 * 
	 */
	public CalcModel()
	{
		clear();
	}
	
	public void setValue() {
		
	}
	
	public void pushNumber(BigDecimal number) {
		numbers.push(number);
	}


	/**
	 * Clears the user values and the calculated value.
	 */
	public void clear()
	{
		history = new Stack();
		numbers = new Stack();
		calcValue = calcValue.ZERO;
	}

	/**
	 * Adds the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to add to the current calculated value by.
	 */
	public void sum(BigDecimal number)
	{
		BigDecimal num1 = (BigDecimal) numbers.pop();
		System.out.println(num1);
		BigDecimal num2 = (BigDecimal) numbers.pop();
		System.out.println(num2);
		calcValue = num2.add(num1);
		
		numbers.push(calcValue);
	}
	
	/**
	 * Subtracts the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to subtract from the current calculated value by.
	 */
	public void subtract(BigDecimal userValue)
	{
		calcValue = calcValue.subtract(userValue);
	}
	
	/**
	 * Multiplies the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 */
	public void multiply(BigDecimal userValue)
	{
		calcValue = calcValue.multiply(userValue);
	}

	/**
	 * Divides the calculated value by a user value.
	 * 
	 * @param userValue
	 *            The value to multiply the current calculated value by.
	 * @pre. userValue is not equivalent to zero.
	 */
	public void divide(BigDecimal userValue)
	{
		calcValue = calcValue.divide(userValue);
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
}
