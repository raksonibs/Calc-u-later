import java.util.ArrayList;

public class FavouritesController
{
	//private static FavouritesPanel f = new FavouritesPanel();
	public static ArrayList<String> a = new ArrayList<String>();
	
	public FavouritesController()
	{
		//a.add("Select Favourite...");
	}
	
	public void saving(String s)
	{
		if (!a.contains(s))
		a.add(s);
		for (int i = 0; i < a.size(); i++)
			System.out.println(a.get(i) + "is goin in");
	}
	
}
