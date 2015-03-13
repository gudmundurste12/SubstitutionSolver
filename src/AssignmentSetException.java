/**
 * Created by Gvendurst on 13.3.2015.
 */
public class AssignmentSetException extends Exception {
	public AssignmentSetException(String message){
		super(message);
	}

	public AssignmentSetException(Exception inner){
		super(inner);
	}

	public AssignmentSetException(String message, Exception inner){
		super(message, inner);
	}
}
