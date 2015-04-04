package CSP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 22.3.2015.
 */
public class SubstitutionCSP{
	private Dictionary dictionary;
	private String encryptedMessage;
	private List<Word> wordsInMessage;

	private List<String> decryptedMessages;


	private static DomainSizeComparator domainSizeComparator;
	private static MostConstrainedVariableHeuristic mostConstrainedVariableHeuristic;
	private static WordHeuristic wordHeuristic;

	public SubstitutionCSP(Dictionary dictionary, String encryptedMessage){
		this.dictionary = dictionary;
		this.encryptedMessage = encryptedMessage;
		this.wordsInMessage = new ArrayList<Word>();
		this.decryptedMessages = new ArrayList<String>();
		for(String s : encryptedMessage.split(" ")){
			wordsInMessage.add(new Word(s));
		}

		trimWordsInMessage();

		domainSizeComparator = new DomainSizeComparator();
		mostConstrainedVariableHeuristic = new MostConstrainedVariableHeuristic();
		wordHeuristic = new WordHeuristic();
	}

	private void trimWordsInMessage(){
		if(wordsInMessage.size() >= 2){
			List<Word> toBeRemoved = new ArrayList<Word>();
			for(int i = 1; i < wordsInMessage.size(); i++){
				for(int j = 0; j < i; j++){
					if(wordsInMessage.get(i) == wordsInMessage.get(j)){
						toBeRemoved.add(wordsInMessage.get(i));
					}
				}
			}

			for(Word w : toBeRemoved){
				wordsInMessage.remove(w);
			}
		}
	}

	public Assignment solve(){

		return CSP_Backtracking(new Assignment(wordsInMessage));
	}

	public Assignment CSP_Backtracking(Assignment a){
		if(a.complete()){
			StringBuilder build = new StringBuilder();
			for(String word : encryptedMessage.split(" ")){
				build.append(a.decrypt(word.toCharArray()) + " ");
			}

			String decrypted = build.toString();

			//We have found a new solution
			if(!decryptedMessages.contains(decrypted)) {
				decryptedMessages.add(decrypted);

				System.out.println(a);
				System.out.println(decrypted);
			}

			//return a;
			return null;
		}


		List<Variable> variablesLeft = a.unassignedVariables();
		//Most constrained variable heuristic
		variablesLeft.sort(mostConstrainedVariableHeuristic);
		variablesLeft.sort(wordHeuristic);

		//Will never be null because of how the algorithm is structured
		Variable X = variablesLeft.get(0);


		//TODO: Sort the domain using a least-constraining value heuristic
		for(Character c : X.freeDomain()){
			//System.out.println(X.cipherLetter + " = " + c);
			a.assign(X.cipherLetter, c);

			if(validAssignment(a)){
				Assignment result = CSP_Backtracking(a);
				if(result != null){
					return result;
				}
			}

			a.unAssign(X.cipherLetter);
			//System.out.println(X.cipherLetter + " != " + c);
		}



		return null;
	}

	public List<Character> sortDomain(Domain d){
		List<Character> returnValue = new ArrayList<Character>();


		return returnValue;
	}

	public boolean validAssignment(Assignment a){
		for(Word word : wordsInMessage){
			if(word != null){
				ValidStatus valid = word.valid(a, dictionary);
				if(valid == ValidStatus.INVALID){
					return false;
				}
			}
		}

		return true;
	}
}
