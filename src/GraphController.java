import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;

public class GraphController
{
	private GraphModel graphModel;
	private CalcModel calcModel;
	private GraphView view;
	private static FavouritesController favC =  new FavouritesController();
	
	public GraphController(JTabbedPane MAIN)
	{
		graphModel = new GraphModel("");
		calcModel = new CalcModel(MAIN);
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

	public void save() {
		System.out.println("Saving "+ graphModel.getExpression());
		favC.saving(graphModel.getExpression());
		
	}

	
}