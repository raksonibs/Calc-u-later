import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GraphModel extends ApplicationFrame {

	public GraphModel(String title) {
		
		super(title);
		
		final XYSeries series = new XYSeries("Sin(x)");
		
	        //This graph basically just samples the equation, creates a bunch of data points
	        //and then connects the data points to one another
	        //Uses JFreeChart
	        //API is here
	        //http://www.jfree.org/jfreechart/api/javadoc/
		
	        double precision = 0.01; //This will affect how smooth the graph is, but also the performance
	        double xLeft = -10;	//The far left of the x axis
	        double xRight = 10; //The far right of x axis
	        
	        //Enters the values
	        for(double i = xLeft; i < xRight; i = i + precision){
	        	
	        	double yValue = Math.sin(i);	//This is where we would use the function
	        	series.add(i, yValue);
	        	
	        }

	        final XYSeriesCollection data = new XYSeriesCollection(series);
	        final JFreeChart chart = ChartFactory.createXYLineChart(
	        	"Sin Wave Graph",
	            "X", //X-axis Name
	            "Y", //Y-axis Name
	            data,	//Dataset
	            PlotOrientation.VERTICAL,	//This will always be vertical for our purposes
	            true,	//Legend
	            true,	//Tool tips
	            false	//URLS, (don't need this)
	        );

	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400)); //Set size of window
	        setContentPane(chartPanel);

	}
	

	public static void main(String[] args) {
		//This would be handled by controller and stuff, but for now this makes it easy to test
	    GraphModel demo = new GraphModel("Graph");
		demo.pack();//Makes it resize-able with the window
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Drawing?");
		final XYSeries series = new XYSeries("Sin(x)");
		
        //This graph basically just samples the equation, creates a bunch of data points
        //and then connects the data points to one another
        //Uses JFreeChart
        //API is here
        //http://www.jfree.org/jfreechart/api/javadoc/
	
        double precision = 0.01; //This will affect how smooth the graph is, but also the performance
        double xLeft = -10;	//The far left of the x axis
        double xRight = 10; //The far right of x axis
        
        //Enters the values
        for(double i = xLeft; i < xRight; i = i + precision){
        	
        	double yValue = Math.sin(i);	//This is where we would use the function
        	series.add(i, yValue);
        	
        }

        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
        	"Sin Wave Graph",
            "X", //X-axis Name
            "Y", //Y-axis Name
            data,	//Dataset
            PlotOrientation.VERTICAL,	//This will always be vertical for our purposes
            true,	//Legend
            true,	//Tool tips
            false	//URLS, (don't need this)
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400)); //Set size of window
        setContentPane(chartPanel);
        System.out.println("Drawed?");
	}

}