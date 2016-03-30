import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JTabbedPane;

public class CalcModelTest {

	private JTabbedPane MAIN = new JTabbedPane();
	private CalcModel c;
	private BigInteger b;
	
	@Before
	public void setUp() throws Exception {
		c = new CalcModel(MAIN);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(true, c.getHistory().isEmpty());
		assertEquals(true, c.getNumbers().isEmpty());
		assertEquals(null, c.lastValue());
	}

	@Test
	public void testClear() {
		
		double num = 7;
		c.pushNumber(BigDecimal.valueOf(num));
		c.clear();
		assertEquals(true, c.getHistory().isEmpty());
		assertEquals(true, c.getNumbers().isEmpty());
		assertEquals(null, c.lastValue());
	}

	@Test
	public void testPush() {
		double num = 7;
		c.pushNumber(BigDecimal.valueOf(num));
		double num2 = 7.0;
		assertEquals("7", c.getExpressionValue());
		c.variable();
		assertEquals("7,X", c.getExpressionValue());
		
	}
	
	@Test
	public void testSubtract() {
		
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.subtract(); 
		BigDecimal num2 = new BigDecimal(-3.0);
		assertEquals(num2, c.lastValue());
		assertEquals("-", c.getLastExpression());
		
		c.subtract();
		c.variable();
		c.subtract();
		
	}
	
	@Test
	public void testSum() {
		
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();
		BigDecimal num2 = new BigDecimal(17);
		assertEquals(num2, c.lastValue());
		assertEquals("+", c.getLastExpression());
		
		c.sum();
		c.variable();
		c.sum();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMultiply() {
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.multiply();
		assertEquals("70", c.lastValue().toPlainString());
		assertEquals("*", c.getLastExpression());
		
		c.multiply();
		c.variable();
		c.multiply();
	}
	
	
	@Test(expected=EmptyStackException.class)
	public void testEmptyDivide(){
		c.divide(); 	//Check division with no input
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivideByZero(){
		//Now test
		c.clear();
		//Ensure it still functions after clearing
		double FirstInput = 3.0;
		c.pushNumber(BigDecimal.valueOf(FirstInput));
		double SecondInput = 0.0;	//Checking divide by zero
		c.pushNumber(BigDecimal.valueOf(SecondInput));
		c.divide();
		assertEquals("3,0", c.getExpressionValue());	//Ensure division didn't take place
	}
	
	@Test
	public void testDivide() {

		assertEquals("", c.getExpressionValue());	
		
		double FirstInput = 8.0;
		c.pushNumber(BigDecimal.valueOf(FirstInput));
		double SecondInput = 4.0; 
		c.pushNumber(BigDecimal.valueOf(SecondInput));
		c.divide();

		BigDecimal ExpectedValue = new BigDecimal(2.0);

		assertEquals(ExpectedValue, c.getCalculatedValue());			//Check BigDecimal
		assertEquals(2.0, c.getCalculatedValue().doubleValue(), 0.01);	//Check conversion to double
		assertEquals("/", c.getLastExpression());						//Check that operator was placed correctly
			

		c.divide();	//Test that operator is substituted
		assertEquals("((8/4)/1)", c.getExpressionValue());
		
		c.variable();		//Now test variables
		c.divide();
		assertEquals("(((8/4)/1)/X)", c.getExpressionValue());
	
	}
	
	@Test
	public void rounding(){
		
		double num = 1;
		c.pushNumber(BigDecimal.valueOf(num));
		assertEquals("1", c.getExpressionValue());
		
	}
	
	@Test
	public void sin(){
		double num =3.14;
		c.pushNumber(BigDecimal.valueOf(num));
		c.sin();
		
		double num2 = 0.0;
		assertEquals(num2, c.lastValue().doubleValue(), 0.15);
		
		c.clear();
		c.variable();
		c.sin();
		assertEquals("(sin(X))", c.getExpressionValue());
	}
	
	@Test
	public void cos(){
		double num =3.14;
		c.pushNumber(BigDecimal.valueOf(num));
		c.cos();
		
		double num2 = -1.0;
		assertEquals(num2, c.lastValue().doubleValue(), 0.15);
		
		c.clear();
		c.variable();
		c.cos();
		assertEquals("(cos(X))", c.getExpressionValue());
	}
	
	@Test
	public void testIsOperator() {
		assertEquals(true, c.isOperator("+"));
		assertEquals(false, c.isOperator("cat"));
	}
	
	@Test
	public void testIsTrig() {
		assertEquals(true, c.isTrignometric("sin"));
		assertEquals(false, c.isTrignometric("cat"));
		
		assertEquals(true, c.isTrignometric("cos"));
		assertEquals(false, c.isTrignometric("1.0"));
	}
	
	@Test
	public void testIsFactorial() {
		assertEquals(true, c.isFactorial("!"));
		assertEquals(false, c.isFactorial("cat"));
	}
	
	@Test
	public void testIsVariable() {
		assertEquals(true, c.isVariable("X"));
		assertEquals(false, c.isVariable("cat"));
	}
	
	@Test
	public void testStackContains() {
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();
		assertEquals(true, c.stackContains(c.getExpressionList(), "+"));
		assertEquals(false, c.stackContains(c.getExpressionList(), "-"));
	}
	
	@Test
	public void expression(){

		
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sin();
		
		c.factorial();
		num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();

		String expression = "(7+10)";
		
		//assertEquals(expression, c.getExpressionValue());

		
	}
	
	@Test
	public void getNumberHistory(){
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();
		c.sin();
		System.out.println(c.getHistory());
		assertEquals("7,10,+,sin", c.getHistory());
		assertEquals("7,10", c.getNumberHistory());
	}
	
	@Test
	public void factorial(){
		double num = 6.0;
		c.pushNumber(BigDecimal.valueOf(num));
		c.factorial();
		assertEquals(720.0, c.lastValue().doubleValue(),20);
		c.clear();
		c.variable();
		c.factorial();
		assertEquals("(X!)", c.getExpressionValue());
	}
	
	@Test
	public void getEquation(){
		c.variable();
		assertEquals("X", c.getEquation());
		c.clear();
		assertEquals("0", c.getEquation());
		double num = 6.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num2 = 4.0;
		c.pushNumber(BigDecimal.valueOf(num2));
		c.sum();
		assertEquals("10.0", c.getEquation());
	}
	
	@Test
	public void getCalcValue(){
		assertEquals(0.0, c.getCalculatedValue().doubleValue(), 0.1);
		double num = 6.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num2 = 4.0;
		c.pushNumber(BigDecimal.valueOf(num2));
		c.sum();
		double num3 = 10.0;
		assertEquals(num3, c.getCalculatedValue().doubleValue(), 0.1);
		assertEquals(num3, c.getCalcValue().doubleValue(), 0.1);
	}
	
	
	
	@Test
	public void undo(){
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();
		c.undo();
		assertEquals("7,10", c.getHistory());
		c.undo();
		assertEquals("7", c.getHistory());
		c.undo();
		assertEquals("", c.getHistory());
		
	}
	
	@Test
	public void printMethods(){
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.sum();
		c.pi();
		c.printAllStacks();
		c.printAsTestCase();
		String[] expressionArray = { "21", "35", "*", "101", "4", "*", "+"};
		String[] inputValuesArray = { "21", "35", "101", "4"};
		String[] numbersArray = { "1139"};
		String[] calculatedValuesArray = { "735", "404", "1139"};
		
		c.generateTestCase(expressionArray, inputValuesArray, numbersArray, calculatedValuesArray);
		c.getTestCase();
	}
	
	

}




