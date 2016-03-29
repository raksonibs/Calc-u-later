import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
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
		assertEquals(true, c.lastValue().doubleValue() == num2);
		assertEquals(false, c.lastValue().doubleValue() == 1.23);

		c.variable();
		assertEquals(true, c.lastValue().doubleValue() == num2);
		assertEquals(false, c.lastValue().doubleValue() == 1.23);
		
	}
	
	@Test
	public void testSubtract() {
		
		double num = 7.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 10.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.subtract(); 
		double num2 = -3.0;
		assertEquals(true, c.lastValue().doubleValue() == num2);
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
		double num2 = 17.0;
		assertEquals(true, c.lastValue().doubleValue() == num2);
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
		double num2 = 70.0;
		assertEquals(true, c.lastValue().doubleValue() == num2);
		assertEquals("*", c.getLastExpression());
		
		c.multiply();
		c.variable();
		c.multiply();
	}
	
	@Test
	public void testDivide() {
		double num = 14.0;
		c.pushNumber(BigDecimal.valueOf(num));
		double num1 = 7.0;
		c.pushNumber(BigDecimal.valueOf(num1));
		c.divide();
		double num2 = 1.0;

		assertEquals(true, c.lastValue().doubleValue() == num2);
		assertEquals("/", c.getLastExpression());
		
		c.divide();
		c.variable();
		c.divide();
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
		assertEquals("sin(X)", c.getExpressionValue());
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
		assertEquals("cos(X)", c.getExpressionValue());
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
		
		assertEquals(expression, c.getExpressionValue());

		
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
		assertEquals("X!", c.getExpressionValue());
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




