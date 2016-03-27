import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel
{
	private int width, height;
	private int counter = 0;
	private boolean flag = false;
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
						&& !box.getSelectedItem().equals("Select Favourite...")
								&& !flag)
				{
					selected = box.getSelectedItem().toString();
					System.out.println("SELECTED: " + selected);
					//Give "selected" to the graph here...
	            	MAIN.setSelectedIndex(1);theController.updateGraph(selected);
				}
				if (e.getStateChange() == ItemEvent.SELECTED
						&& !box.getSelectedItem().equals("Select Favourite...")
							&& flag)
				{
					box.removeItem(box.getSelectedItem());
					flag = false;
				}
			}
		});
		
		JButton b = new ButtonAdapter("DELETE")
		{
			public void pressed()
			{
				System.out.println("DELETING ACTIVATED");
//				box.removeItem(selected);
//				box.setSelectedItem("Select Favourite...");
//				favC.a.remove(selected);
				flag = true;
			}
		};
		
		add(b);
		if (flag){b.setBackground(Color.CYAN);}
		else{b.setBackground(Color.WHITE);}
		add(box);
	}

	public void addToFavourites(String expression)
	{
		System.out.println("in the adding method with " + expression);
		box.addItem(expression);
		for (int i = 0; i < favC.a.size(); i++)
		{
			System.out.println(box.getItemAt(i) + "is inside");
		}
	}
	
	public void clearList(){box.removeAllItems();}
	
	public int getCounter(){return counter;}

} // end of ControlPanel class

