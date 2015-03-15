package CSP;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Variable {
	public char plainLetter;
	public Domain domain;

	public Variable(char plainLetter){
		this.plainLetter = plainLetter;
		this.domain = new Domain(true);
	}
}
