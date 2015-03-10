/**
 * Created by Gvendurst on 10.3.2015.
 */
public class Assignment {
	public char cipherLetter;
	public char plainLetter;


	public Assignment(char cipherLetter, char plainLetter){
		this.cipherLetter = cipherLetter;
		this.plainLetter = plainLetter;
	}

	@Override
	public boolean equals(Object other){
		if(other instanceof Assignment){
			Assignment otherAssignment = (Assignment)other;

			return otherAssignment.cipherLetter == this.cipherLetter
					&& otherAssignment.plainLetter == this.plainLetter;
		}
		else if(other instanceof Character){
			return (Character)other == cipherLetter;
		}
		else{
			return false;
		}
	}
}
