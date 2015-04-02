package CSP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Variable {
	public final char cipherLetter;
	public Character plainLetter;
	public Domain domain;

	public Variable(char cipherLetter, Character plainLetter){
		this.cipherLetter = cipherLetter;
		this.plainLetter = plainLetter;
		this.domain = new Domain(true);
	}

	public boolean assign(char plainLetter){
		if(domain.available(plainLetter)){
			this.plainLetter = plainLetter;
			//TODO: Make unavailable for other variables
			return true;
		}

		return false;
	}

	public void unAssign(){
		domain.makeAvailable(plainLetter);
		plainLetter = null;

	}

	public List<Character> freeDomain(){
		List<Character> returnValue = new ArrayList<Character>();
		for(Character c : domain.availability.keySet()){
			if(domain.availability.get(c).available){
				returnValue.add(c);
			}
		}

		return returnValue;
	}

	@Override
	public String toString(){
		if(plainLetter != null){
			return cipherLetter + ": " + plainLetter;
		}
		else{
			return cipherLetter + ": unassigned";
		}
	}
}
