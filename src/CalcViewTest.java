import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.Stack;

import javax.swing.JTabbedPane;

import org.junit.Before;
import org.junit.Test;

public class CalcViewTest {


	public static CalcController controller;
	public  JTabbedPane tab;
	public CalcView view = new CalcView(controller, tab);
	
	@Before
	public void setup(){
		
		tab = new JTabbedPane();
		controller = new CalcController(tab);
		view = new CalcView(controller, tab);
	}
	

	@Test
	//4.2.1
	public void setGetUserValue(){
		
		view.setUserValue("123456789.0");
		assertEquals("123456789.0", view.getUserValue());
		
		view.setUserValue("");
		assertEquals("", view.getUserValue());
		
		view.setUserValue("+-/*!sin(X)cos(X)");
		assertEquals("+-/*!sin(X)cos(X)", view.getUserValue());
		
		view.setUserValue("ABCDEFG");
		assertEquals("ABCDEFG", view.getUserValue());
		
	}
	
	@Test
	//4.2.2
	public void containsUserValue(){
		
		assertEquals(false, view.containsUserValue());
		
		view.setUserValue("123456789.0");
		assertEquals(true, view.containsUserValue());
		
		view.setUserValue("+-/*!sin(X)cos(X)");
		assertEquals(true, view.containsUserValue());
		
		view.setUserValue("");
		assertEquals(false, view.containsUserValue());
		
	}
	
	@Test
	public void registerButton(){
	
		//view.setUserValue("10");
		//view.addToHistory(controller);
		
	}
	
	@Test
	public void roundingValue(){
		
		String number = "123";
		assertEquals("123",view.findRoundingValue(number));
		
		number = "1.23";
		assertEquals("1.23",view.findRoundingValue(number));
		
		number = "-0.23456456";
		assertEquals("-0.23456456",view.findRoundingValue(number));
	}

	
	@Test
	public void changeInputButton(){
		
		
		double number = 10.0;
		view.changeInputButton(number);
		
	}
	
	@Test
	public void clearUserInput(){
		
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
		view.setUserValue("1");
		view.clearUserValue();
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
		view.setUserValue("-123.01");
		view.clearUserValue();
		assertEquals("", view.getUserValue());
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
	}
	
	@Test
	public void keyPressa() throws AWTException{
		
		 Robot robot = new Robot();
	     // Simulate a mouse click
	     robot.mousePress(InputEvent.BUTTON1_MASK);
	     robot.mouseRelease(InputEvent.BUTTON1_MASK);

	}
	
	@Test
	public void getExpression(){
		
		view.setExpressionValue("1+1");
		assertEquals("1+1", view.getExpressionValue());
		
		view.setExpressionValue("(1+0.2)*(sin(X))");
		assertEquals("(1+0.2)*(sin(X))", view.getExpressionValue());
		
	}
	
	@Test
	public void setCalculatedValue(){
		
		view.setCalcValue("25");
		
	}
	
	@Test
	public void setHistory(){
		
		view.setHistory("1,1,+");
		
	}

}