import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import javax.swing.JTabbedPane;

public class CalcControllerTest {

	private JTabbedPane MAIN = new JTabbedPane();
	private CalcModel model = new CalcModel(MAIN);
	private GraphModel graphModel = new GraphModel("");
	
	private CalcController control;
	
	@Before
	public void setUp() throws Exception {
		 control = new CalcController(model, graphModel, MAIN);
	}
	
	@Test
	public void setUpBasic(){
		control = new CalcController(MAIN);
	}
	
	@Test
	public void rounding(){
		control.addToRounding("123");
	}

	
}