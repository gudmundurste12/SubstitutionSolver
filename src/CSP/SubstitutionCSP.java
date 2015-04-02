package CSP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gvendurst on 22.3.2015.
 */
public class SubstitutionCSP{
	private Dictionary dictionary;
	private String encryptedMessage;
	private String[] wordsInMessage;


	private static DomainSizeComparator domainSizeComparator;

	public SubstitutionCSP(Dictionary dictionary, String encryptedMessage){
		this.dictionary = dictionary;
		this.encryptedMessage = encryptedMessage;
		this.wordsInMessage = encryptedMessage.split(" ");
		domainSizeComparator = new DomainSizeComparator();
	}

	public Assignment solve(){

		return CSP_Backtracking(new Assignment());
	}

	public Assignment CSP_Backtracking(Assignment a){
		if(a.complete()){
			return a;
		}


		List<Variable> variablesLeft = a.unassignedVariables();
		//Most constrained variable heuristic
		variablesLeft.sort(domainSizeComparator);

		//Will never be null because of how the algorithm is structured
		Variable X = variablesLeft.get(0);


		//TODO: Sort the domain using a least-constraining value heuristic
		for(Character c : X.freeDomain()){
			a.assign(X.cipherLetter, c);

			if(validAssignment(a)){
				Assignment result = CSP_Backtracking(a);
				if(result != null){
					return result;
				}
			}

			a.unAssign(X.cipherLetter);

		}



		return null;
	}

	public List<Character> sortDomain(Domain d){
		List<Character> returnValue = new ArrayList<Character>();


		return returnValue;
	}

	public boolean validAssignment(Assignment a){
		for(String word : wordsInMessage){
			if(word != null){
				String decrypted = a.decrypt(word);
				if(decrypted != null) {
					if (!dictionary.contains(decrypted)) {
						return false;
					}
				}
			}
		}

		return true;
	}
}
