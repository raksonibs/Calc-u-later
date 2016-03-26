import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel
{
	private int width, height;
	Graph temp;
	JComboBox box;

	public FavouritesPanel()
	{
		box = new JComboBox();
		box.addItem("Select Favourite...");
		box.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED
						&& !(box.getSelectedItem()
								.equals("Select Favourite...")))
				{
					temp.Operation(box.getSelectedItem().toString(), 0);
				}
			}
		});
		add(box);
	}

	public void addToFavourites(String expression)
	{
		box.addItem(expression);
	}

} // end of ControlPanel class


