import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class CalcModelTest {


	private CalcModel c;
	private BigInteger b;
	
	@Before
	public void setUp() throws Exception {
		c = new CalcModel();
	}

	@Test
	public void testClear() {
		c.clear();
		assertEquals(b.ZERO,c.getCalcValue());
	}

	@Test
	public void testSum() {
		
		
		assertEquals(b.TEN, c.getCalcValue());
		
	}
	
	@Test
	public void testSubtract() {
		
		
		assertEquals(new BigInteger("-2"), c.getCalcValue());
		
	}
	
	@Test
	public void testMultiply() {
		
		
		assertEquals(b.ZERO, c.getCalcValue());
		
	}
	
	@Test
	public void testDivide() {
		
		
		assertEquals(b.ZERO, c.getCalcValue());
		
	}
	

}




