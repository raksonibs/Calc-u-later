import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel
{
	private int width, height;
	private int counter = 0;
	private String selected;
	private FavouritesController favC =  new FavouritesController();
	private GraphModel graph;
	Graph temp;
	JComboBox<String> box;

	public FavouritesPanel(final JTabbedPane MAIN, final CalcController theController)
	{
		box = new JComboBox<String>();
		box.addItem("Select Favourite...");
		box.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED
						&& !(box.getSelectedItem()
								.equals("Select Favourite...")))
				{
					selected = box.getSelectedItem().toString();
					System.out.println("SELECTED: " + selected);
					//Give "selected" to the graph here...
	            	MAIN.setSelectedIndex(1);
					theController.updateGraph(selected);
				}
			}
		});
		
		JButton b = new ButtonAdapter("DELETE")
		{
			public void pressed()
			{
				if (!(box.getSelectedItem().equals("Select Favourite...")))
				{
					System.out.println("DELETING " + selected);
					box.removeItem(selected);
					box.setSelectedItem("Select Favourite...");
					favC.a.remove(selected);
				}
			}
		};
		
		add(b);
		add(box);
	}

	public void addToFavourites(String expression)
	{
		System.out.println("in the adding method with " + expression);
		if (!expression.equals("Select Favourite...")) {			
			box.addItem(expression);
		}
	}
	
	public void clearList(){box.removeAllItems();}
	
	public int getCounter(){return counter;}

} // end of ControlPanel class

