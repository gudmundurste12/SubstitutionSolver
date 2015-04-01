package CSP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Variable {
	//TODO: Add cipherletter here. Too much indirect dependency on Assignment here.
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

	public List<Character> freeDomain(){
		List<Character> returnValue = new ArrayList<Character>();
		for(Character c : domain.availability.keySet()){
			if(domain.availability.get(c).available){
				returnValue.add(c);
			}
		}

		return returnValue;
	}
}
