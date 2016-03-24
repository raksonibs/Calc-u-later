import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GraphModel
{
	private XYSeries series;
	private XYSeriesCollection data;

	public GraphModel()
	{
		super();
		this.series = new XYSeries("Graph(x)");
		this.data = new XYSeriesCollection(series);
	}

	public void draw(CalcModel model)
	{
		// need to get last expression pressed
		System.out.println("Inside draw");

		String expression = model.getLastExpression();

		System.out.println(expression);
		double precision = 0.01; // This will affect how smooth the graph is,
									// but also the performance
		double xLeft = -10; // The far left of the x axis
		double xRight = 10; // The far right of x axis

		// Enters the values
		double yValue;
		for (double i = xLeft; i < xRight; i = i + precision)
		{

			if (expression == "cos")
			{
				yValue = Math.cos(i);
			}
			else
			{
				yValue = Math.sin(i);
			}

			this.series.add(i, yValue);

		}

		System.out.println("Draw finished");

	}

	public void draw(String selected)
	{
		double precision = 0.01; // This will affect how smooth the graph is,
		// but also the performance
		double xLeft = -10; // The far left of the x axis
		double xRight = 10; // The far right of x axis

		// Enters the values
		double yValue;
		for (double i = xLeft; i < xRight; i = i + precision)
		{

			if (selected == "cos")
			{
				yValue = Math.cos(i);
			}
			else
			{
				yValue = Math.sin(i);
			}

			this.series.add(i, yValue);

		}
	}

	public void clear()
	{
		this.series = new XYSeries("Graph(x)");
		this.data = new XYSeriesCollection(series);
	}

	public XYSeries getSeries()
	{
		return series;
	}

	public XYSeriesCollection getSeriesCollection()
	{
		return data;
	}
}