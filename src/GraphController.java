import org.jfree.chart.ChartPanel;

public class GraphController
{

	private CalcModel model;
	private GraphModel graphModel;
	
	public GraphController()
	{
		//test.init();
		model = new CalcModel();
		graphModel = new GraphModel("");

	}
	
	public GraphController(CalcModel modelIn, GraphModel graphModelIn){
		
		model = modelIn;
		graphModel = graphModelIn;
		
	}

	public ChartPanel getChartPanel(){
		
		String expression = model.getEquation();
		graphModel.pushExpression(expression);

		return graphModel.getChartPanel();

	}
	
}