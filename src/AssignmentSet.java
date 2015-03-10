import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 10.3.2015.
 */
public class AssignmentSet {
	private List<Assignment> assignments;

	public AssignmentSet(){
		assignments = new ArrayList<Assignment>(26);
	}

	public void addAssignment(Assignment a){
		assignments.add(a);
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
}
