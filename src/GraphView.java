
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

	public static void addComponentsToPane(Container pane, 
			final GraphController graphController)
	{
	
			//graphPanel.add(graphController.updateGraph());
			pane.add(graphPanel);
			pane.setVisible(true);
			
		System.out.println("Test");

		addComponentsToPane(this, theController);
//	  temp = new Graph();
//		
//	  requestFocus();

		this.setVisible(true);

	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridBagLayout());
        panel.add(filler);
        return panel;
    }
     
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CalcView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }



	@SuppressWarnings("serial")
	public static void addComponentsToPane(Container pane,
			final GraphController theController)
	{
		if (RIGHT_TO_LEFT)
		{
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
		{
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		calcText = new JTextField(20);
		calcText.setEditable(false);
		userValueText = new JTextField(5);
		history = new JTextField(20);
		history.setEditable(false);
		history.setText("Start a new calculation");
		expressionList = new JTextField(20);
		expressionList.setEditable(false);
		
		if (shouldWeightX) {
			c.weightx = 0.5;
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		pane.add(calcText, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2;
		pane.add(new JLabel("Calculated Value"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		pane.add(history, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 2;
		pane.add(new JLabel("History"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		pane.add(expressionList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		pane.add(new JLabel("Expression"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		pane.add(userValueText, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 2;
		pane.add(new JLabel("Input"), c);

		
	}

	/**
	 * Get the string value of the user input text field.
	 * 
	 * @return The string in the user input text field.
	 */
	public static String getUserValue()
	{
		String userValue = userValueText.getText();
		System.out.println("User Value is: " + userValue);
		return userValue;

	}

	
	/**
	 * Check to see if the user has entered a value into the text field
	 * 
	 * @return false if the text field is empty
	 */
	public boolean containsUserValue()
	{
		if (userValueText.getText().equals(""))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public static void registerButton(String button,
			GraphController theController)
	{
		String his = history.getText();		

		

		if (button.equals("Graph")) {
			System.out.println("Create panel");
			//ChartPanel chPanel = new ChartPanel(chart); //creating the chart panel, which extends JPanel
			//chPanel.setPreferredSize(new Dimension(785, 440)); //size according to my window
			
			JPanel jPanel = new JPanel();

			jPanel.add(theController.getChartPanel()); //add the chart viewer to the JPanel

			JFrame newWindow = new JFrame();
			newWindow.setPreferredSize(new Dimension(500,500));
			newWindow.add(jPanel);
			newWindow.setVisible(true);
			newWindow.pack();
		}	

		else if (button.equals("SAVE"))
		{
			System.out.println("SAVING...");
			favC.saving(expressionList.getText().toString());
		}
		
		// fixed negate button
		else if (button.equals("+/-"))
		{

			String userVal = userValueText.getText();
			char changeSign = userVal.charAt(0);
			if (!userVal.equals("0"))
			{
				if (changeSign == '-')
				{
					userVal = userVal.substring(1, userVal.length());
					userValueText.setText(userVal);
				}
				else
				{
					userVal = '-' + userVal;
					userValueText.setText(userVal);
				}
			}

		}
		else if (button.equals("."))
		{

			String userVal = userValueText.getText();

			if (userVal.length() > 1 && userVal.charAt(1) == '.')
			{
				userVal = userVal.substring(1, his.length());
				userValueText.setText(userVal);
			}
			else
			{

				if (userVal.length() > 0)
				{
					userVal = userVal + ".";
				}
				else
				{
					userVal = "0." + userVal;
				}
				userValueText.setText(userVal);
			}

		}
	}

	public void updateGraph(ChartPanel graph){
		
		//Update chart

	}
	
}

