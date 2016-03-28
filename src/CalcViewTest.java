import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Stack;

import javax.swing.JTabbedPane;

import org.junit.Before;
import org.junit.Test;

public class CalcViewTest {

	public CalcView view;
	public CalcController controller;
	public  JTabbedPane tab;
	
	@Before
	public void setup(){
		
		tab = new JTabbedPane();
		controller = new CalcController(tab);
		view = new CalcView(controller, tab);
	}
	

	@Test
	public void setGetUserValue(){
		
		view.setUserValue("123456789.0");
		assertEquals("123456789.0", view.getUserValue());
		
		view.setUserValue("");
		assertEquals("", view.getUserValue());
		
		view.setUserValue("+-/*!sin(X)cos(X)");
		assertEquals("+-/*!sin(X)cos(X)", view.getUserValue());
		
	}
	
	@Test
	public void containsUserValue(){
		
		assertEquals(false, view.containsUserValue());
		
		view.setUserValue("123456789.0");
		assertEquals(true, view.containsUserValue());
		
		view.setUserValue("+-/*!sin(X)cos(X)");
		assertEquals(true, view.containsUserValue());
		
		view.setUserValue("");
		assertEquals(false, view.containsUserValue());
		
	}
	
}