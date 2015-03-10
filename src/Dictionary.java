import java.util.HashSet;

/**
 * Created by Gvendurst on 10.3.2015.
 * Stores words in a way that makes looking up incomplete words easy and fast
 */
public class Dictionary {
	private static HashSet<String> dictionary;

	public Dictionary(){
		dictionary = new HashSet<String>();
	}

	public void add(String s){
		dictionary.add(s.toLowerCase());
	}

	public boolean contains(String s){
		return dictionary.contains(s.toLowerCase());
	}
}
