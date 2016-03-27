import java.util.ArrayList;

public class FavouritesController
{
	private static FavouritesPanel f = new FavouritesPanel();
	public static ArrayList<String> a;
	
	public FavouritesController()
	{
		a = new ArrayList<String>();
	}
	
	public void saving(String s)
	{
		a.add(s);
	}
	
}
