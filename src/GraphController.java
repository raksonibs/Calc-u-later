import org.jfree.chart.ChartPanel;

public class GraphController
{

	private GraphModel graphModel;
	private GraphView view;
	public GraphController()
	{
		graphModel = new GraphModel("");
		view = new GraphView(this);
		view.setVisible(true);

	}
	public GraphController(GraphModel modelIn)
	{
		graphModel = modelIn;
		view = new GraphView(this);
		view.setVisible(true);

	}


	public ChartPanel getChartPanel(){
		
		String expression = "sin(X)";
		graphModel.pushExpression(expression);
		return graphModel.getChartPanel();

	}
	
}