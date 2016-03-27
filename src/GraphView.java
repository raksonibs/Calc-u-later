import java.awt.Container;

import javax.swing.JPanel;


public class GraphView extends JPanel{

	
	public GraphView(final GraphController graphController){
		super();
		addComponentsToPane(this, graphController);
		this.setVisible(true);
		
	}

	public static void addComponentsToPane(Container pane, 
			final GraphController graphController)
	{
			JPanel panel = new JPanel();
			panel.add(graphController.getChartPanel());
			pane.add(panel);
			pane.setVisible(true);
			
	}
}
