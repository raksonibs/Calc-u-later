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
		
		c.sum(b.TEN);
		assertEquals(b.TEN, c.getCalcValue());
		
	}
	
	@Test
	public void testSubtract() {
		
		c.subtract(new BigInteger("2"));
		assertEquals(new BigInteger("-2"), c.getCalcValue());
		
	}
	
	@Test
	public void testMultiply() {
		
		c.multiply(new BigInteger("2"));
		assertEquals(b.ZERO, c.getCalcValue());
		
	}
	
	@Test
	public void testDivide() {
		
		c.divide(new BigInteger("2"));
		assertEquals(b.ZERO, c.getCalcValue());
		
	}
	

}




