import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalcController
{
	/**
	 * String for sum command.
	 */
	public static final String SUM = "SUM";
	
	/**
	 * String for subtract command.
	 */
	public static final String SUBTRACT = "SUBTRACT";
	
	/**
	 * String for multiply command.
	 */
	public static final String MULTIPLY = "MULTIPLY";
	
	/**
	 * String for divide command.
	 */
	public static final String DIVIDE = "DIVIDE";
	
	/**
	 * String for clear command.
	 */
	public static final String CLEAR = "CLEAR";
	
	private CalcModel model;
	private CalcView view;

	/**
	 * Creates a controller for the given view and model.
	 * 
	 * @param view
	 *            The view.
	 * @pre. view != null
	 * @param theModel
	 *            The model the controller uses
	 * @pre. model != null
	 */
	public CalcController()
	{
		model = new CalcModel();
		view = new CalcView(this);
		view.setVisible(true);
		model.clear();
	}
	
	public CalcController(CalcModel modelIn){
		
		model = modelIn;
		view = new CalcView(this);
		view.setVisible(true);
		model.clear();
		
	}
//	  NTD: should be bigint array
	public void sum() {
		
		model.sum();
		showValue();
	}
	
	public void subtract() {
		model.subtract();
		showValue();
	}

	public void multiply() {
		model.multiply();
		showValue();
	}

	public void divide() {
		if(!model.lastValue().equals(BigDecimal.ZERO)) {
			model.divide();
		}
		showValue();
	}

	public void cos() {
		model.cos();
		showValue();
	}

	public void factorial() {
		model.factorial();
		showValue();
	}

	public void sin() {
		model.sin();
		showValue();
	}

	public void clear() {
		model.clear();
		empty();
		view.setHistory("Start a new calculation");
		view.setExpressionValue("");
		view.setButtonClicked();
	}

	public void setValue(int value) {
		
	}

	private void showValue() {
		BigDecimal calcValue = model.getCalcValue();
		String expressionValue = model.getExpressionValue();
		
		System.out.println("Current value is: " + calcValue.toString());
		System.out.println("Expression is: " + expressionValue);
		
		view.setCalcValue(view.findRoundingValue(calcValue.toString()));
		view.setExpressionValue(expressionValue);
		//view.setCalcValue(calcValue.toString());
		
	}

	public void empty()
	{
		System.out.println("clearing...");
		view.setCalcValue("");
		view.clearUserValue();
	}
	
	public void undo() {
		model.undo();
	}
	
	//When ENTER is pushed
	public void addValue(BigDecimal value) {
		model.pushNumber(value);
	}
	
	public void addExpression(String valueIn) 
	{
		model.pushExpression(valueIn);
	}
	
	public void updateInput(String value){
		
		view.setUserValue(model.updateUserInput(value));
	}
}
