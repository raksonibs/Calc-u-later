import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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
		assertEquals("÷", c.getLastExpression());
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
	

}




