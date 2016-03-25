import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FavouritesPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 142501195135828204L;
	private int width, height;
	private int number = 1;
	Graph temp;
	JComboBox<String> box;

	public FavouritesPanel()
	{
//		this.width = width1;
//		this.height = height1;
//		f1 = new JButton("Favourites 1");
//		JButton f2 = new JButton("Favourites 2");
//		JButton f3 = new JButton("Favourites 3");a.add(f3);
//		JButton f4 = new JButton("Favourites 4");a.add(f4);
//		JButton f5 = new JButton("Favourites 5");a.add(f5);
//		JButton f6 = new JButton("Favourites 6");a.add(f6);
//		JButton f7 = new JButton("Favourites 7");a.add(f7);
//		JButton f8 = new JButton("Favourites 8");a.add(f8);
//		JButton f9 = new JButton("Favourites 9");a.add(f9);
//		JButton f10 = new JButton("Favourites 10");a.add(f10);
//		add(f1);add(f2);add(f3);add(f4);add(f5);add(f6);add(f7);add(f8);add(f9);add(f10);
//		setLayout(new FlowLayout());
	    setPreferredSize(new Dimension(200,200));
		box = new JComboBox<String>();
		box.addItem("Select Favourite...");
		box.addItemListener( new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED && box.getSelectedItem().equals("Select Favourite..."));
				{
					temp.Operation(box.getSelectedItem().toString(), 0);
				}
			}
		});
		add(box);
		
	}
	
	public void addToFavourites(String expression)
	{
		System.out.println("AT LEAST IM IN HERE");
		box.addItem(expression);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}

