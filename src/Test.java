import java.math.BigDecimal;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double in = 25.0;

		in = factorial(in);
		BigDecimal value = new BigDecimal(in);
		
		System.out.println(value.toPlainString());
		
	}
	
	
	public static double factorial(double b)
	{
		double r = 1.0;
		System.out.println("working with: " + b);
		while (b > 1.0)
		{
			r = r * b;
			b -= 1;
			System.out.println(r);
		}
		return r;
	}

}
