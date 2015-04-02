package CSP;

import java.util.Comparator;

/**
 * Created by Gvendurst on 2.4.2015.
 */
public class MostConstrainedVariableHeuristic implements Comparator<Variable> {

	@Override
	/**
	 * Returns -1 if o1 is less than o2
	 * Returns 0 if o1 is equal to o2
	 * Returns 1 if o1 is greater than o2
	 */
	public int compare(Variable o1, Variable o2) {
		int h1 = o1.mostConstrainedHeuristic();
		int h2 = o2.mostConstrainedHeuristic();
		if(h1 < h2){
			return -1;
		}
		else if(h1 > h2){
			return 1;
		}

		return 0;
	}
}
