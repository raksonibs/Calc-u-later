import java.util.ArrayList;

public class FavouritesController
{
	private static FavouritesPanel f;
	public static ArrayList<String> a;
	
	public FavouritesController()
	{
		f = new FavouritesPanel();
		a = new ArrayList<String>();
	}
	
	public void saving(String s)
	{
		a.add(s);
	}
	
}
