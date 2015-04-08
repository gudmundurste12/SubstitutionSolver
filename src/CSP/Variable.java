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
	private static DomainComparator domainComparator;

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

		if(domainComparator == null){
			domainComparator = new DomainComparator();
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
		//TODO: Sorting the domain would be done here, because domain.availability.keySet() is in alphabetical order by default
		List<Character> returnValue = new ArrayList<Character>();
		for(Character c : domain.availability.keySet()){
			if(domain.availability.get(c).available){
				returnValue.add(c);
			}
		}

		returnValue.sort(domainComparator);

		return returnValue;
	}

	public int mostConstrainedHeuristic(){
		return totalWords - inWords.size();
	}

	public double wordHeuristic(){
		double returnValue = Integer.MAX_VALUE;
		for(Word w : inWords){
			returnValue = Math.min(returnValue, w.heuristic());
		}

		return returnValue;
	}

	public String inverseString(){
		if(plainLetter != null){
			return plainLetter + ": " + cipherLetter;
		}
		else{
			return plainLetter + ": unassigned";
		}
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
