package CSP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Domain {
	public Map<Character, Availability> availability;
	private int domainSize;

	public Domain(boolean autoFill){
		availability = new HashMap<Character, Availability>();

		if(autoFill){
			availability.put('a', new Availability());
			availability.put('b', new Availability());
			availability.put('c', new Availability());
			availability.put('d', new Availability());
			availability.put('e', new Availability());
			availability.put('f', new Availability());
			availability.put('g', new Availability());
			availability.put('h', new Availability());
			availability.put('i', new Availability());
			availability.put('j', new Availability());
			availability.put('k', new Availability());
			availability.put('l', new Availability());
			availability.put('m', new Availability());
			availability.put('n', new Availability());
			availability.put('o', new Availability());
			availability.put('p', new Availability());
			availability.put('q', new Availability());
			availability.put('r', new Availability());
			availability.put('s', new Availability());
			availability.put('t', new Availability());
			availability.put('u', new Availability());
			availability.put('v', new Availability());
			availability.put('w', new Availability());
			availability.put('x', new Availability());
			availability.put('y', new Availability());
			availability.put('z', new Availability());
			domainSize = 26;
		}
	}

	public boolean available(char letter){
		Availability a = availability.get(letter);

		return a.available;
	}

	public void makeUnavailable(char letter){
		Availability a = availability.get(letter);

		if(a.available){
			a.available = false;
			a.deletedBy = letter;
			domainSize--;
		}
	}

	public int domainSize(){
		return domainSize;
	}
}
