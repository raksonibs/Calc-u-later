import java.awt.Container;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;


public class GraphView extends JPanel{

	static JPanel graphPanel = new JPanel();
	
	public GraphView(final GraphController graphController){
		super();
		addComponentsToPane(this, graphController);
		this.setVisible(true);
		
	}

	public static void addComponentsToPane(Container pane, final GraphController graphController)
	{
			pane.add(graphPanel);
			pane.setVisible(true);
			
	}

	public void updateGraph(ChartPanel graph) {
		
		graphPanel.removeAll();
		graphPanel.add(graph);
		graphPanel.setVisible(true);

	}
	

}
