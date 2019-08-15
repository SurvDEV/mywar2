package businessLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loader 
{
	public static Random rand = new Random();
	
	public static List<String> loadDico(String filePath)
	{
		ArrayList<String> res = new ArrayList<String>();
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String mot = br.readLine();
			while(mot != null)
			{
				res.add(mot);
				mot = br.readLine();
			}
			br.close();
			return res;
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
			return null;
		}
	}
	
	public static boolean aGagne(boolean[] lettreTrouvees)
	{
		for (int i = 0; i < lettreTrouvees.length; i++) if(!lettreTrouvees[i]) return false;
		return true;
	}
	
	public static boolean[] lettreTrouvees(char[] mot, ArrayList<Character> lettresJouees)
	{
		boolean[] res = new boolean[mot.length];
		for (int i = 0; i < mot.length; i++) 
		{
			if(lettresJouees.contains(mot[i])) res[i] = true;
		}
		return res;
	}
	
	public static String randomMot(ArrayList<String> dico)
	{
		return dico.get(rand.nextInt(dico.size()));
	}
}
