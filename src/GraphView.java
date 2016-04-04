import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;


public class GraphView extends JPanel{

	private static JPanel graphPanel = new JPanel();
	
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CalcView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	public GraphView(final GraphController graphController){
		super();
		addComponentsToPane(this, graphController);
		this.setVisible(true);
		
	}

	public static void addComponentsToPane(Container pane, final GraphController graphController)
	{
	    ImageIcon blueIconEWide = createImageIcon("/ButtonIcons/ExtraWideIconB.png");
		JButton button = new ButtonAdapter("Save"){
			public void pressed() {
				graphController.save();
			}
		};
		
		button.setPreferredSize(new Dimension(400,30));
		button.setIcon(blueIconEWide);
	    button.setPreferredSize(new Dimension(button.WIDTH,30));
	    button.setHorizontalTextPosition(button.CENTER);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
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
