import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel
{
	private int width, height;
	private int counter = 0;
	private boolean flag = false;
	public boolean fromFavourites = false;
	public String selected;
	private FavouritesController favC =  new FavouritesController();
	private GraphModel graph;
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
					fromFavourites = true;
	            	MAIN.setSelectedIndex(1);
				}
				if (e.getStateChange() == ItemEvent.SELECTED
						&& !box.getSelectedItem().equals("Select Favourite...")
							&& flag)
				{
					favC.a.remove(box.getSelectedItem());
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
				flag = true;
			}
		};
		
		b.setToolTipText("HOW TO DELETE: Press DELETE then select the expression you wish to delete.");
		
		add(b);
		if (flag){b.setBackground(Color.CYAN);}
		else{b.setBackground(Color.WHITE);}
		add(box);
	}
	
	public JComboBox<String> getBox() {
		return box;
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

