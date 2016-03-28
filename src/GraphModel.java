import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class GraphModel extends ApplicationFrame {
	private XYSeries series;
	private XYSeriesCollection data;
	private Expression e;

    double precision = 0.01; //This will affect how smooth the graph is, but also the performance
    double tolerance = 2.5; //How much a point can differ from a previous point before being set to null
    double xLeft = -5;	//The far left of the x axis
    double xRight = 5;//The far right of x axis
    
    double previous = 0;
    double current = 0;
    double difference = 0;
    double prevDif = 0;
    
    String expression = "0";
    String expressionDirty = "";
    
    public GraphModel(String title) {
      super(title);
    }
    
    public String getExpression() {
    	return expression;
    }
    
    public ChartPanel getChartPanel(){
      
      XYSeries series = new XYSeries(expression);
        //Enters the values

      
      for(double i = xLeft; i < xRight; i = i + precision){

       String expressionString = expression;
       
        	if(expression.contains("!") && expression.contains("X")){		//Replace variable in factorial with appropriate numbers
        		expressionString = expression.replace("!", fact(i));
        	}
        	
          e = new ExpressionBuilder(expressionString)
          .variables("X", "y")
          .build()
          .setVariable("X", i);
          double result = e.evaluate();

            if(expression.contains("X"))	//If we're dealing with a variable we have to account for asymptotes
            {
            	/*
            	 * We check if the differences between two values starts to get too large
            	 * If its getting really big really quickly, its likely approaching an asymptote
            	 * This can cause issues with things like factorial, in which case the
            	 * tolerance needs to be adjusted to compensate.
            	 */
             current = result;
	            difference = Math.abs(current - previous);	//Check the absolute value of the difference between current result and previous result

	            			
	            //System.out.println(difference);
	            
	            if(difference < prevDif * tolerance){
	            	//System.out.println("Within Bounds");
	                series.add(i, result);
	            }
	            else
	            {
	            	//System.out.println("Out of specified bounds");	//If out of bounds, don't connect the points

	            	series.add(i,null);
	            }
            }
            else	//Other if theres no variables, were just drawing a straight line
            {
            	series.add(i,result);
            }
            
            prevDif = difference;
            previous = current;		
            
          }

          XYSeriesCollection data = new XYSeriesCollection(series);
          JFreeChart chart = ChartFactory.createXYLineChart(
           expressionDirty,
           "X", //X-axis Name
           "Y", //Y-axis Name
           data,	//Dataset
           PlotOrientation.VERTICAL,	//This will always be vertical for our purposes
           true,	//Legend
           true,	//Tool tips
           false	//URLS, (don't need this)
           );

          ChartPanel chartPanel = new ChartPanel(chart);
          chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));		

          return chartPanel;

        }
        
	/**
	 * Set left bound of graph
	 * @param value
	 */
	public void setLeftBound(double value){
		xLeft = value;
	}
	
	/**
	 * Set right bound of graph
	 * @param value
	 */
	public void setRightBound(double value){
		xRight = value;
	}
	
	/**
	 * Push an expression into the graph model
	 * @param input
	 */
	public void pushExpression(String input){
    
		expressionDirty = input;
		
		while(expressionDirty.contains(",") && expressionDirty.length()>0){
			System.out.println("Comma at : " + expressionDirty.indexOf(","));
			expressionDirty = expressionDirty.substring(input.indexOf(",") + 1, input.length());
		}
		expressionDirty = "y = " + expressionDirty;
		
		expression = clean(input);
		System.out.println("Graph Model: expression is " + expression);
		
	}
	
	/**
	 * Change signs to appropriate operators and shave off commas
	 * @param dirty input
	 * @return String: cleaned input
	 */
	public String clean(String input){
		//Replace with appropriate operators
		input = input.replace("*", "*");
		input = input.replace("÷", "/");

		//Removes all commas
		while(input.contains(",")){
			System.out.println("Comma at : " + input.indexOf(","));
			input = input.substring(input.indexOf(",") + 1, input.length());
		}
		if(!input.contains("X") && input.contains("!")){
			
			//input.replace(oldChar, newChar)
		}
		//System.out.println("New expression is: " + input);
		
		return input;
	}

	/**
	 * Compute the factorial of a double and return it as a String
	 * @param b
	 * @return
	 */
	public String fact(double b)
	{
		double r = 1.0;
		System.out.println("working with: " + b);
		while (b > 1.0)
		{
			r = r * b;
			b -= 1;
			System.out.println(r);
		}
		return Double.toString(r);
	}

	/**
	 * Resets all values in graphModel
	 */
	public void reset(){
	     precision = 0.01; //This will affect how smooth the graph is, but also the performance
	     tolerance = 2.5; //How much a point can differ from a previous point before being set to null
	     xLeft = -5;	//The far left of the x axis
	     xRight = 5;//The far right of x axis
      
	     previous = 0;
	     current = 0;
	     difference = 0;
	     prevDif = 0;
       
	     expression = "0";
	     expressionDirty = "";
      
    }
	
	public ChartPanel getChart(){
		//Returns the chartPanel
		return getChartPanel();

	}
    
}