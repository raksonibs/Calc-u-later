import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CalcController
{
	
	mainClass test;
	int width = test.WIDTH;
	int height = test.HEIGHT;
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
	
	private static FavouritesPanel f = new FavouritesPanel();
	public static ArrayList<String> a = new ArrayList<String>();

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
		view = new CalcView(this);
		view.setVisible(true);
		//model.clear();
	}
	
	public CalcController(CalcModel modelIn, GraphModel graphModelIn){
		
		model = modelIn;
		graphModel = graphModelIn;
		//graph = new GraphPanel(width,height);
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
		model.divide();

		showValue();
	}
	
	public void pi() {
		model.pi();
		showValue();
	}

	public void cos() {
		pushUserText();
		model.cos();
		showValue();
	}

	public void factorial() {
		model.factorial();
		showValue();
	}

	public void sin() {
		pushUserText();
		model.sin();
		showValue();
	}

	public void clear() {
		model.clear();
		graphModel.reset();
		empty();
		view.setHistory("Start a new calculation");
		view.setExpressionValue("");
		view.setButtonClicked();
	}
	public void Graph(){
		
	}
	
	public void variable(){
		model.variable();
		showValue();
	}

	public void setValue(int value) {
		
	}

	private void showValue() 
	{
		//System.out.println("Current value is: " + calcValue.toPlainString());
		//System.out.println("Expression is: " + expressionValue);
		
		view.setCalcValue(view.findRoundingValue(model.getCalculatedValue().toPlainString()));
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
		String expressionValue = model.getExpressionValue();
		
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
	
	public void printInfoToConsole(){
		
		model.printAllStacks();

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
	
	public void updateGraph(){
		String expression = model.getExpressionValue();
		graphModel.pushExpression(expression);
		
	}
	


}