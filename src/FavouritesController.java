import java.util.ArrayList;

public class FavouritesController
{
	//private static FavouritesPanel f = new FavouritesPanel();
	public static ArrayList<String> a = new ArrayList<String>();
	
	public FavouritesController()
	{
	}
	
	public void saving(String s)
	{
		a.add(s);
	}
	
}
