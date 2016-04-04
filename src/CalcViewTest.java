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
	
	private JTabbedPane tab = new JTabbedPane();
	private CalcModel model = new CalcModel(tab);
	private GraphModel graphModel = new GraphModel("");
	private CalcController controller;
	private CalcView view;
	
	
	@Before
	public void setup(){
		controller = new CalcController(model, graphModel, tab);
		view = new CalcView(controller, tab);
	}
	

	@Test
	//3.3.1
	public void setAndGetUserValue(){
		
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
	//3.3.2
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
	//3.3.3
	public void clearUserInput(){
		
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
		view.setUserValue("X");
		view.clearUserValue();
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
		view.setUserValue("-123.01");
		view.clearUserValue();
		assertEquals("", view.getUserValue());
		assertEquals("", view.getExpressionValue());
		
	}
	
	
	@Test
	//3.3.4
	public void setAndGetExpression(){
		
		view.setExpressionValue("1+1");
		assertEquals("1+1", view.getExpressionValue());
		
		view.setExpressionValue("(1+0.2)*(sin(X))");
		assertEquals("(1+0.2)*(sin(X))", view.getExpressionValue());
		
	}
	
	
	@Test
	//3.3.5
	public void reigsterButtonPressOperators(){
		
		setUpValues();
		view.registerButton("+", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("Graph", controller);
		controller.Graph();
		assertEquals("", view.getUserValue());
		
		setUpValues();
		
		view.registerButton("-", controller);
		assertEquals("", view.getUserValue());
		setUpValues();
		
		view.registerButton("*", controller);
		assertEquals("", view.getUserValue());
		setUpValues();
		
		view.registerButton("/", controller);
		assertEquals("", view.getUserValue());
		setUpValues();
		
		setUpValues();
		view.registerButton("!", controller);
		assertEquals("", view.getUserValue());
		
		view.setUserValue("10");
		view.registerButton("UNDO", controller);
		view.registerButton("UNDO", controller);
		
		view.registerButton("UNDO", controller);
		assertEquals("", view.getUserValue());
		setUpValues();
		
		view.registerButton("sin", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("cos", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("X", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("π", controller);
		assertEquals("", view.getUserValue());
		
		setUpValues();
		view.registerButton("SAVE", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("Test", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("Clear", controller);
		controller.clear();
		assertEquals("", view.getUserValue());
		
		view.registerButton("TEST", controller);
		assertEquals("", view.getUserValue());
		
		view.registerButton("INFO", controller);
		assertEquals("", view.getUserValue());
	}
	
	@Test
	public void registerButtonPressNumbers(){
		
		view.setUserValue("");
		view.registerButton(".", controller);
		view.setUserValue("123");
		view.registerButton(".", controller);
		
		view.setUserValue("0");
		view.registerButton("+/-", controller);
		view.registerButton("+/-", controller);
		view.setUserValue("1");
		view.registerButton("+/-", controller);
		view.registerButton("+/-", controller);
		
		view.changeInputButton(1);
		view.changeInputButton(2);
		view.changeInputButton(3);
		view.changeInputButton(4);
		view.changeInputButton(5);
		view.changeInputButton(6);
		view.changeInputButton(7);
		view.registerButton(".", controller);
		view.changeInputButton(8);
		view.changeInputButton(9);
		view.changeInputButton(0);

		
		assertEquals("11234567.890", view.getUserValue());
		
		view.clearUserValue();
		view.changeInputButton(3.14159);
		
		assertEquals("3.14159", view.getUserValue());
		
	}
	
	@Test
	public void pushValue(){
		view.setUserValue("10");
		
	}
	
	
	@Test
	//3.3.5
	public void setCalculatedValue(){
		
		view.setUserValue("25");
		view.registerButton("+", controller);
	}
	
	@Test
	public void setHistory(){
		
		view.setHistory("1,1,+");
		
	}

	@Test
	public void setUpValues(){
		
		view.setUserValue(String.valueOf((Math.random()*100)));
		view.addToHistory(controller);
		view.setUserValue(String.valueOf((Math.random()*100)));
		view.addToHistory(controller);
		
	}
	
	@Test
	public void decimalTest() {
		//view.registerButton("Clear", controller);
		view.setUserValue("0.123");

		view.registerButton(".", controller);
		
		
		view.getUserValue();
		assertEquals("123", view.getUserValue());
	}
}