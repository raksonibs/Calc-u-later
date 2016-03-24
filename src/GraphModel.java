import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GraphModel extends ApplicationFrame {

	public GraphModel(String title) throws EvaluationException {
	
		  super(title);

	        //This graph basically just samples the equation, creates a bunch of data points
	        //and then connects the data points to one another
	        //Uses JFreeChart
	        //JFreeChart http://www.jfree.org/jfreechart/
	        //API http://www.jfree.org/jfreechart/api/javadoc/
		  
	        //We can enter an equation as a String to be evaluated
		  	//Uses JEval
		    //JEval http://jeval.sourceforge.net/
		    //API http://jeval.sourceforge.net/docs/api/index.html
		  
		  	//using a as the variable for now
	        String expressionEquation = "sin(a) * pow(a,2)";	//String to be evaluated --> needs to be pulled from CalcModel expression
	        final XYSeries series = new XYSeries(expressionEquation); //The data set for evaluated points
	   
	        double precision = 0.037; //This will affect how smooth the graph is, but also the performance. The weird number because it's less likely to land 
	        						  //on an asymptote like zero which is pretty common
	        
	        double xLeft = -Math.PI ; //The far left of the x axis
	        double xRight = Math.PI ; //The far right of x axis
	        double yValue = 0;	//Initialize the y variable
	        
	        Evaluator evaluator = new Evaluator();	//JEval's evaluator for evaluating strings

	        System.out.println("The expression is: " + expressionEquation);
	   
	        
	        //Replace the operators with appropriate computer like operators
	        expressionEquation = expressionEquation.replace("×", "*");
	        expressionEquation = expressionEquation.replace("÷", "/");


	        //Enters the values
	        for(double i = xLeft; i < xRight; i = i + precision){

	        	//replace variable "a" with a number
		        String equation = expressionEquation.replace("a", Double.toString(i));
		        //System.out.println(equation);
		        
		        //Try evaluate the string
		        try
		        {
					String finalequation = evaluator.evaluate(equation);
					yValue = Double.parseDouble(finalequation);
		        	series.add(i, yValue);
		        }
		        //If we can't (for example an asymptote), we give a null value which will draw a blank line
		        catch (Exception e)
		        {
		        	series.add(i, null);
		        	System.out.println("We couldn't evaluate that");
		        }

	        }

	        final XYSeriesCollection data = new XYSeriesCollection(series); //Adds the dataset into a collection
	        final JFreeChart chart = ChartFactory.createXYLineChart(	//Create the XY chart with appropriate parameters
	            expressionEquation,
	            "X", //X-axis Name
	            "Y", //Y-axis Name
	            data,	//Dataset
	            PlotOrientation.VERTICAL,	//This will always be vertical for our purposes
	            true,	//Legend
	            true,	//Tool tips
	            false	//URLS, (don't need this)
	        );

	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400)); //Set size of window, should probably be size of calculator
	        setContentPane(chartPanel);

	}

	public static void main(String[] args) throws EvaluationException {
		//This would be handled by controller and stuff, but for now this makes it easy to test
		GraphModel demo = new GraphModel("Graph");
		demo.pack();//Makes it resize-able with the window
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		}

}