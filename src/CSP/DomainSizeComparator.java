package CSP;

import java.util.Comparator;

/**
 * Created by Gvendurst on 23.3.2015.
 */
public class DomainSizeComparator implements Comparator<Variable> {

	@Override
	/**
	 * Returns -1 if o1 is less than o2
	 * Returns 0 if o1 is equal to o2
	 * Returns 1 if o1 is greater than o2
	 */
	public int compare(Variable o1, Variable o2) {
		if(o1.domain.domainSize() < o2.domain.domainSize()){
			return -1;
		}
		if(o1.domain.domainSize() > o2.domain.domainSize()){
			return 1;
		}
		return 0;
	}
}
