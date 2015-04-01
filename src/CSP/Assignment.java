package CSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gvendurst on 22.3.2015.
 */
public class Assignment {
	private Map<Character, Variable> variables;

	public Assignment(){
		variables = new HashMap<Character, Variable>();

		variables.put('A', new Variable(null));
		variables.put('B', new Variable(null));
		variables.put('C', new Variable(null));
		variables.put('D', new Variable(null));
		variables.put('E', new Variable(null));
		variables.put('F', new Variable(null));
		variables.put('G', new Variable(null));
		variables.put('H', new Variable(null));
		variables.put('I', new Variable(null));
		variables.put('J', new Variable(null));
		variables.put('K', new Variable(null));
		variables.put('L', new Variable(null));
		variables.put('M', new Variable(null));
		variables.put('N', new Variable(null));
		variables.put('O', new Variable(null));
		variables.put('P', new Variable(null));
		variables.put('Q', new Variable(null));
		variables.put('R', new Variable(null));
		variables.put('S', new Variable(null));
		variables.put('T', new Variable(null));
		variables.put('U', new Variable(null));
		variables.put('V', new Variable(null));
		variables.put('W', new Variable(null));
		variables.put('X', new Variable(null));
		variables.put('Y', new Variable(null));
		variables.put('Z', new Variable(null));
	}

	public Variable get(char cipherLetter){
		return variables.get(cipherLetter);
	}

	public boolean assign(char cipherLetter, char plainLetter){
		Variable v = get(cipherLetter);
		return v.assign(plainLetter);
	}

	public boolean complete(){
		for(Variable v : variables.values()){
			if(v.plainLetter == null){
				return false;
			}
		}

		return true;
	}

	/**
	 * Decrypts a string if possible.
	 * @param encrypted The encrypted string
	 * @return The decrypted string or null, if a character could not be decryted.
	 */
	public String decrypt(String encrypted){
		String returnValue = "";

		for(char c : encrypted.toCharArray()){
			Character current = variables.get(c).plainLetter;
			if(current == null){
				return null;
			}
			returnValue += current;
		}

		return returnValue;
	}

	public List<Variable> unassignedVariables(){
		List<Variable> returnValue = new ArrayList<Variable>();
		for(Variable v : variables.values()){
			if(v.plainLetter == null){
				returnValue.add(v);
			}
		}


		return returnValue;
	}
}
