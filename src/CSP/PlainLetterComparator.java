package CSP;

import java.util.Comparator;

/**
 * Created by Gvendurst on 5.4.2015.
 */
public class PlainLetterComparator implements Comparator<Variable> {
	@Override
	/**
	 * Returns -1 if o1 is less than o2
	 * Returns 0 if o1 is equal to o2
	 * Returns 1 if o1 is greater than o2
	 */
	public int compare(Variable o1, Variable o2) {
		if(!Character.isLetter(o1.plainLetter) && Character.isLetter(o2.plainLetter)){
			return 1;
		}
		else if(Character.isLetter(o1.plainLetter) && !Character.isLetter(o2.plainLetter)){
			return -1;
		}
		else{
			return o1.plainLetter.compareTo(o2.plainLetter);
		}

	}
}
