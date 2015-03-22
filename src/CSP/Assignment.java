package CSP;

import java.util.HashMap;
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
}
