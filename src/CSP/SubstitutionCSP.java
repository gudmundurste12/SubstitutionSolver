package CSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		for(String s : encryptedMessage.split("\\s*[^A-Z]+")){
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

	public Assignment solve(boolean findAll){

		return CSP_Backtracking(new Assignment(wordsInMessage), findAll);
	}

	public Assignment CSP_Backtracking(Assignment a, boolean findAll){
		if(a.complete()){
			if(findAll){
				StringBuilder build = new StringBuilder();

				for(char c : encryptedMessage.toCharArray()){
					build.append(a.decrypt(c));
				}

				String decrypted = build.toString();

				//We have found a new solution
				if(!decryptedMessages.contains(decrypted)) {
					decryptedMessages.add(decrypted);

					System.out.println(decrypted);

					System.out.print("Do you want to keep searching(Y/N)? ");

					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					String input;

					try{
						input = in.readLine();

						if(input.toUpperCase().equals("Y")){
							return null;
						}
						else{
							return a;
						}
					}
					catch(IOException e){
						System.out.println(e.getMessage());
						System.exit(1);
					}
				}

				return null;
			}
			else{
				return a;
			}
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
				Assignment result = CSP_Backtracking(a, findAll);
				if(result != null){
					return result;
				}
			}

			a.unAssign(X.cipherLetter);
			//System.out.println(X.cipherLetter + " != " + c);
		}



		return null;
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
