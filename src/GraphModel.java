import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;




public class GraphModel extends ApplicationFrame {
	private XYSeries series;
	private XYSeriesCollection data;
	private Expression e;

    double precision = 0.01; //This will affect how smooth the graph is, but also the performance
    double xLeft = -10;	//The far left of the x axis
    double xRight = 10;//The far right of x axis
	
	String expression = "";
	
	public GraphModel(String title) {
		super(title);
	}
	
	public ChartPanel getChartPanel(){
		
		XYSeries series = new XYSeries(expression);
        //Enters the values
        for(double i = xLeft; i < xRight; i = i + precision){
           	
    		e = new ExpressionBuilder(expression)
            .variables("X", "y")
            .build()
            .setVariable("X", i);
            double result = e.evaluate();

        	series.add(i, result);

        }
		
		XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
           expression,
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
	
	public void setLeftBound(double value){
		xLeft = value;
	}
	public void setRightBound(double value){
		xRight = value;
	}
	
	public void pushExpression(String input){
				
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
		input = input.replace("×", "*");
		input = input.replace("÷", "/");
		
		
		//Removes all commas
		while(input.contains(",")){
			System.out.println("Comma at : " + input.indexOf(","));
			input = input.substring(input.indexOf(",") + 1, input.length());
		}
		
		//System.out.println("New expression is: " + input);
		
		return input;
	}
	

        
	}
	












