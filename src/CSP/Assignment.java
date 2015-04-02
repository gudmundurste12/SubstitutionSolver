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

		variables.put('A', new Variable('A', null));
		variables.put('B', new Variable('B', null));
		variables.put('C', new Variable('C', null));
		variables.put('D', new Variable('D', null));
		variables.put('E', new Variable('E', null));
		variables.put('F', new Variable('F', null));
		variables.put('G', new Variable('G', null));
		variables.put('H', new Variable('H', null));
		variables.put('I', new Variable('I', null));
		variables.put('J', new Variable('J', null));
		variables.put('K', new Variable('K', null));
		variables.put('L', new Variable('L', null));
		variables.put('M', new Variable('M', null));
		variables.put('N', new Variable('N', null));
		variables.put('O', new Variable('O', null));
		variables.put('P', new Variable('P', null));
		variables.put('Q', new Variable('Q', null));
		variables.put('R', new Variable('R', null));
		variables.put('S', new Variable('S', null));
		variables.put('T', new Variable('T', null));
		variables.put('U', new Variable('U', null));
		variables.put('V', new Variable('V', null));
		variables.put('W', new Variable('W', null));
		variables.put('X', new Variable('X', null));
		variables.put('Y', new Variable('Y', null));
		variables.put('Z', new Variable('Z', null));
	}

	public Variable get(char cipherLetter){
		return variables.get(cipherLetter);
	}

	public boolean assign(char cipherLetter, char plainLetter){
		Variable v = get(cipherLetter);
		if(v.assign(plainLetter)){
			for(Variable var : unassignedVariables()){
				var.domain.makeAvailable(plainLetter);
			}

			return true;
		}
		else {
			return false;
		}
	}

	public void unAssign(char cipherLetter){
		get(cipherLetter).unAssign();
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
	/*
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
	*/

	/**
	 * Decrypts a string if possible.
	 * @param encrypted The encrypted string
	 * @return The decrypted string or null, if a character could not be decryted.
	 */
	public String decrypt(char[] encrypted){
		String returnValue = "";

		for(char c : encrypted){
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

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		System.out.println("Building");
		for(Variable var : variables.values()){
			System.out.println("Adding variable");
			builder.append(var);
			builder.append("\r\n");
		}
		System.out.println("Done building");

		return builder.toString();
	}
}
