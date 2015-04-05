package CSP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Variable{
	public final char cipherLetter;
	public Character plainLetter;
	public Domain domain;
	public List<Word> inWords;
	private static int totalWords;

	public Variable(char cipherLetter, Character plainLetter, List<Word> listOfWords){
		this.cipherLetter = cipherLetter;
		this.plainLetter = plainLetter;
		this.domain = new Domain(true);
		this.inWords = new ArrayList<Word>();

		totalWords = listOfWords.size();
		for(Word w : listOfWords){
			if(w.contains(cipherLetter)){
				inWords.add(w);

			}
		}

		//This letter does not appear in the message, and should therefore not be examined
		if(inWords.size() == 0){
			this.plainLetter = '-';
		}
	}

	public boolean assign(char plainLetter){
		if(domain.available(plainLetter)){
			this.plainLetter = plainLetter;
			for(Word w : inWords){
				w.decrementNumberOfCharactersLeft();
			}
			return true;
		}

		return false;
	}

	public void unAssign(){
		domain.makeAvailable(cipherLetter, plainLetter);
		for(Word w : inWords){
			w.incrementNumberOfCharactersLeft();
		}
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

	public int mostConstrainedHeuristic(){
		return domain.domainSize() * (totalWords - inWords.size() + 1);
	}

	public int wordHeuristic(){
		int returnValue = Integer.MAX_VALUE;
		for(Word w : inWords){
			returnValue = Math.min(returnValue, w.heuristic());
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
