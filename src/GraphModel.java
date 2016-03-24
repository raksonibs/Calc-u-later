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

	public GraphModel(String title) {
		super(title);
	}
	

	public static void main(String[] args) {
		//This would be handled by controller and stuff, but for now this makes it easy to test
//	    GraphModel demo = new GraphModel("Graph");
//		demo.pack();//Makes it resize-able with the window
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);
	}

	public void draw(CalcModel model) {
		// need to get last expression pressed
	    double precision = 0.01; //This will affect how smooth the graph is, but also the performance
        double xLeft = -10;	//The far left of the x axis
        double xRight = 10; //The far right of x axis
       
       //Enters the values
        for (double i = xLeft; i < xRight; i = i + precision){
       	
        	double yValue = Math.sin(i);	//This is where we would use the function
        	this.series.add(i, yValue);
       	
       }
        
	}
	
	

}