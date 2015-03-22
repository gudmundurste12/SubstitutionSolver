package CSP;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Variable {
	public Character plainLetter;
	public Domain domain;

	public Variable(Character plainLetter){
		this.plainLetter = plainLetter;
		this.domain = new Domain(true);
	}

	public boolean assign(char plainLetter){
		if(domain.available(plainLetter)){
			this.plainLetter = plainLetter;
			return true;
		}

		return false;
	}
}
