package CSP;

import java.util.*;

/**
 * Created by Gvendurst on 22.3.2015.
 */
public class Assignment {
	private Map<Character, Variable> variables;

	public Assignment(List<Word> listOfWords){
		variables = new HashMap<Character, Variable>();

		variables.put('A', new Variable('A', null, listOfWords));
		variables.put('B', new Variable('B', null, listOfWords));
		variables.put('C', new Variable('C', null, listOfWords));
		variables.put('D', new Variable('D', null, listOfWords));
		variables.put('E', new Variable('E', null, listOfWords));
		variables.put('F', new Variable('F', null, listOfWords));
		variables.put('G', new Variable('G', null, listOfWords));
		variables.put('H', new Variable('H', null, listOfWords));
		variables.put('I', new Variable('I', null, listOfWords));
		variables.put('J', new Variable('J', null, listOfWords));
		variables.put('K', new Variable('K', null, listOfWords));
		variables.put('L', new Variable('L', null, listOfWords));
		variables.put('M', new Variable('M', null, listOfWords));
		variables.put('N', new Variable('N', null, listOfWords));
		variables.put('O', new Variable('O', null, listOfWords));
		variables.put('P', new Variable('P', null, listOfWords));
		variables.put('Q', new Variable('Q', null, listOfWords));
		variables.put('R', new Variable('R', null, listOfWords));
		variables.put('S', new Variable('S', null, listOfWords));
		variables.put('T', new Variable('T', null, listOfWords));
		variables.put('U', new Variable('U', null, listOfWords));
		variables.put('V', new Variable('V', null, listOfWords));
		variables.put('W', new Variable('W', null, listOfWords));
		variables.put('X', new Variable('X', null, listOfWords));
		variables.put('Y', new Variable('Y', null, listOfWords));
		variables.put('Z', new Variable('Z', null, listOfWords));
	}

	public Variable get(char cipherLetter){
		return variables.get(cipherLetter);
	}

	public boolean assign(char cipherLetter, char plainLetter){
		Variable v = get(cipherLetter);
		if(v.assign(plainLetter)){
			for(Variable var : unassignedVariables()){
				var.domain.makeUnavailable(cipherLetter, plainLetter);
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	public void unAssign(char cipherLetter){
		Variable theVariable = get(cipherLetter);
		char plainLetter = theVariable.plainLetter;
		theVariable.unAssign();
		for(Variable var : variables.values()){
			var.domain.makeAvailable(cipherLetter, plainLetter);
		}
	}

	public boolean complete(){
		for(Variable v : variables.values()){
			if(v.plainLetter == null){
				return false;
			}
		}

		return true;
	}

	public String inverseString(){
		StringBuilder builder = new StringBuilder();

		for(Variable var : variablesByPlainLetter()){
			builder.append(var.inverseString());
			builder.append("\r\n");
		}

		return builder.toString();
	}

	private List<Variable> variablesByPlainLetter(){
		List<Variable> returnValue = new ArrayList<Variable>(variables.values());

		Collections.sort(returnValue, new PlainLetterComparator());

		return returnValue;
	}

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

	public char decrypt(char encrypted){
		Variable current = variables.get(encrypted);
		if(current != null){
			return current.plainLetter;
		}
		else{
			return encrypted;
		}
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
		for(Variable var : variables.values()){
			builder.append(var);
			builder.append("\r\n");
		}

		return builder.toString();
	}
}
