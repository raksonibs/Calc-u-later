import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JTabbedPane;

public class GraphModelTest {

	private GraphModel model;
	
	@Before
	public void setup(){
		
		model = new GraphModel("");
	}
	
	@Test
	//6.2.1
	public void pushExpression(){
		model.pushExpression("sin(X) - cos(X)");
		assertEquals("sin(X) - cos(X)",model.getExpression());
		
		model.pushExpression("-123456780.9+1-1*2/2");
		assertEquals("-123456780.9+1-1*2/2", model.getExpression());
		
		model.pushExpression("6!");
		assertEquals("6!", model.getExpression());

		model.pushExpression("1,2");
		assertEquals("2", model.getExpression()); 
		
		model.pushExpression(",");
		assertEquals("", model.getExpression());

	}
	
	@Test
	//6.2.2
	public void getExpression(){
		
		String expression = model.getExpression();
		assertEquals("0", expression);
		
		model.pushExpression("sin(X)");
		expression = model.getExpression();
		assertEquals("sin(X)", expression);
		
		model.pushExpression("1");
		expression = model.getExpression();
		assertEquals("1", expression);
		
	}
	
	@Test
	//6.2.3
	public void bounds(){
		model.setLeftBound(3.1);
		model.setRightBound(0.01);
		
		assertEquals(3.1, model.xLeft, 0.01);
		assertEquals(0.01, model.xRight, 0.01);

	}
	
	@Test
	//6.2.4
	public void cleanExpression(){
		
		String expression = "1,sin(X)";
		assertEquals("sin(X)", model.clean(expression));
		
		expression = "1,Cos(X),Sin(X),3.1456";
		assertEquals("3.1456", model.clean(expression));
		
	}
	
	@Test
	//6.2.5
	public void factorial(){
		
		String result = model.fact(6);
		assertEquals("720.0", result);
		
		result = model.fact(-3);
		assertEquals("1.0", result);
		
	}
	
	@Test
	//6.2.6
	public void reset(){
		
		model.precision = 10;
	    model.tolerance = 15;
	    model.xLeft = -564;	
	    model.xRight = 5.78556;
	    
	    model.previous = 0;
	    model.current = 1;
	    model.difference = 3;
	    model.prevDif = 9;
		
		model.reset();
		
	    double precision = 0.01;
	    double tolerance = 2.5;
	    double xLeft = -5;	
	    double xRight = 5;
	    
	    double previous = 0;
	    double current = 0;
	    double difference = 0;
	    double prevDif = 0;
	    
	    String expression = "0";
	    String expressionDirty = "";
	    
		assertEquals(precision, model.precision, 0.01);
		assertEquals(tolerance, model.tolerance, 0.01);
		assertEquals(xLeft, model.xLeft, 0.01);
		assertEquals(xRight, model.xRight, 0.01);
		
		assertEquals(previous, model.previous, 0.01);
		assertEquals(current, model.current, 0.01);
		assertEquals(difference, model.difference, 0.01);
		assertEquals(prevDif, model.prevDif, 0.01);

		assertEquals(expression, model.expression);
		assertEquals(expressionDirty, model.expressionDirty);
	}
	
	@Test
	public void getChartPanel(){
		model.pushExpression("(sin(X))!");
		model.getChartPanel();
		model.reset();
		model.pushExpression("X!");
		model.getChartPanel();
		model.pushExpression("X");
		model.getChartPanel();
		model.pushExpression("1");
		model.getChartPanel();
	
	}
	
	@Test
	public void getChart(){
		model.pushExpression("sin(X)");
		model.getChartPanel();
		model.getChart();
	}
	
}
