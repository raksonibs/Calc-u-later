import java.io.IOException;
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
	private GraphModel graphModel;
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
	
	public CalcController(CalcModel modelIn, GraphModel graphModelIn){
		
		model = modelIn;
		graphModel = graphModelIn;
		view = new CalcView(this);
		view.setVisible(true);
		model.clear();
		
	}
//	  NTD: should be bigint array
	public void sum() {
		
		pushUserText();
		model.sum();
		showValue();
	}
	
	public void subtract() {
		pushUserText();
		model.subtract();
		showValue();
	}

	public void multiply() {
		pushUserText();
		model.multiply();
		showValue();
	}

	public void divide() {
		pushUserText();
		if(!model.lastValue().equals(BigDecimal.ZERO)) {
			model.divide();
		}
		showValue();
	}
	
	public void pi() {
		model.pi();
		showValue();
	}


	public void factorial() {
		pushUserText();
		model.factorial();
		showValue();
	}

	public void sin() {
		pushUserText();
		model.sin();
		showValue();
	}

	public void cos() {
		pushUserText();
		model.cos();
		showValue();
	}
	
	public void variable(){
		pushUserText();
		model.variable();
		showValue();
		
	}
	
	public void clear() {
		model.clear();
		empty();
		view.setHistory("Start a new calculation");
		view.setExpressionValue("");
		//view.setButtonClicked();
	}

	public void setValue(int value) {
		
	}

	private void showValue() 
	{
		//System.out.println("Current value is: " + calcValue.toPlainString());
		//System.out.println("Expression is: " + expressionValue);
		
		view.setCalcValue(model.getCalculatedValue().toPlainString());
		view.setExpressionValue(model.getExpressionValue());
		view.setHistory(model.getHistory());
		
	}

	public void empty()
	{
		System.out.println("clearing...");
		view.setCalcValue("");
		view.clearUserValue();
		model.clear();
	}
	
	public void undo() {
		model.undo();
		showValue();
		
	}

	//When ENTER is pushed
	public void addValue(BigDecimal value) {
		
		model.pushNumber(value);
		view.setHistory(model.getHistory());
		
	}
	
	public void addToRounding(String value){
		
		model.updateRounding(value);
		
	}
	/**
	 * Prints stack information from model to the console
	 */
	public void printInfoToConsole(){
		
		model.printAllStacks();
		model.printAsTestCase();
	}

	
	/**
	 * If the user has entered a value into the text field without pushing enter, this will
	 * push the value into the stacks
	 * As per requirements document
	 */
	public void pushUserText()
	{
		if(view.containsUserValue() == true){
			String userText = view.getUserValue();
			BigDecimal value = new BigDecimal(userText);
			addValue(value);
			view.clearUserValue();
		}
	}
	
	public void runTestCase(){
		model.getTestCase();
		showValue();
	}

}
