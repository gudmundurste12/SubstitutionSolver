import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 10.3.2015.
 *
 * Stores assignments so that no variable will be assigned two values.
 */
public class AssignmentSet {
	private List<Assignment> assignments;

	public AssignmentSet(){
		assignments = new ArrayList<Assignment>(26);
	}

	/**
	 * Returns true if the assignment was added, but false otherwise
	 * @param a The assignment to be added
	 * @return
	 */
	public boolean addAssignment(Assignment a){
		for(Assignment x : assignments){
			if(x.equals(a.cipherLetter)){
				return false;
			}
		}

		assignments.add(a);
		return true;
	}

	public Assignment getAssignment(char c){
		for(Assignment a : assignments){
			if(a.equals(c)){
				return a;
			}
		}

		return null;
	}

	public void removeAssignment(Assignment a){
		assignments.remove(a);
	}

	public boolean completeAssignment(){
		boolean returnValue = true;
		if(assignments.size() != 26){
			return false;
		}

		return returnValue;
	}

	public String decryptWord(String theString) throws AssignmentSetException{
		StringBuilder builder = new StringBuilder();

		for(char c : theString.toCharArray()){
			try{
				builder.append(getAssignment(c).plainLetter);
			}
			catch(NullPointerException e){
				throw new AssignmentSetException("AssignmentSet contains no assignment for value: " + c);
			}
		}

		return builder.toString();
	}
}
