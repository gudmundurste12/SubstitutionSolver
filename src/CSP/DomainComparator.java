package CSP;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Gvendurst on 8.4.2015.
 */
public class DomainComparator implements Comparator<Character> {
	private HashMap<Character, Integer> charValues;

	private void InitializeCharValues(){
		charValues = new HashMap<Character, Integer>();

		charValues.put('a', 1);
		charValues.put('i', 2);
		charValues.put('s', 3);
		charValues.put('t', 4);
		charValues.put('m', 5);
		charValues.put('n', 6);
		charValues.put('d', 7);
		charValues.put('e', 8);
		charValues.put('y', 9);
		charValues.put('w', 10);
		charValues.put('o', 11);
		charValues.put('c', 12);
		charValues.put('h', 13);
		charValues.put('r', 14);
		charValues.put('b', 15);
		charValues.put('f', 16);
		charValues.put('g', 17);
		charValues.put('l', 18);
		charValues.put('u', 19);
		charValues.put('k', 20);
		charValues.put('p', 21);

		charValues.put('j', 22);




		charValues.put('q', 23);


		charValues.put('v', 24);



		charValues.put('x', 25);

		charValues.put('z', 26);
		charValues.put('-', 9000);
	}

	@Override
	public int compare(Character o1, Character o2) {
		if(charValues == null){
			InitializeCharValues();
		}


		return Integer.compare(charValues.get(o1), charValues.get(o2));
	}
}
