import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;


public class GraphView extends JPanel{

	private static JPanel graphPanel = new JPanel();
	
	
	
	public GraphView(final GraphController graphController){
		super();
		addComponentsToPane(this, graphController);
		this.setVisible(true);
		
	}

	public static void addComponentsToPane(Container pane, final GraphController graphController)
	{
		JButton button = new ButtonAdapter("Save"){
			public void pressed() {
				graphController.save();
			}
		};
		
		button.setPreferredSize(new Dimension(400,30));
		pane.add(graphPanel);
		pane.add(button);
		pane.setVisible(true);
			
	}

	public void updateGraph(ChartPanel graph) {
		
		graphPanel.removeAll();
		graphPanel.add(graph);
		graphPanel.setVisible(true);

	}
	

}
