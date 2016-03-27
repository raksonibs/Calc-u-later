import org.jfree.chart.ChartPanel;

public class GraphController
{
	private GraphModel graphModel;
	private CalcModel calcModel;
	private GraphView view;
	
	public GraphController()
	{
		graphModel = new GraphModel("");
		calcModel = new CalcModel();
		view = new GraphView(this);
		view.setVisible(true);
		graphModel.reset();

	}
	public GraphController(CalcModel calcModelIn,GraphModel modelIn)
	{
		graphModel = modelIn;
		calcModel = calcModelIn;
		view = new GraphView(this);
		view.setVisible(true);

	}

	public void updateGraph(){
		
		view.updateGraph(graphModel.getChart());
		
	}
	
}