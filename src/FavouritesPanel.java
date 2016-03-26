import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel
{
	private int width, height;
	private int counter = 0;
	private String selected;
	private FavouritesController favC =  new FavouritesController();
	Graph temp;
	JComboBox<String> box;

	public FavouritesPanel()
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
					//Give "selected" to the graph here...
				}
			}
		});
		
		JButton b = new ButtonAdapter("DELETE")
		{
			public void pressed()
			{
				System.out.println("DELETING " + selected);
				box.removeItem(selected);
				box.setSelectedItem("Select Favourite...");
				favC.a.remove(selected);
			}
		};
		
		add(b);
		add(box);
	}

	public void addToFavourites(String expression)
	{
		System.out.println("in the adding method with " + expression);
		box.addItem(expression);
		if (box.getItemAt(1).equals(expression)){System.out.println("IT SHOULD WORK"); counter++;} 
	}
	
	public void clearList(){box.removeAllItems();}
	
	public int getCounter(){return counter;}

} // end of ControlPanel class

