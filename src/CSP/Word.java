package CSP;

/**
 * Created by Gvendurst on 2.4.2015.
 */
public class Word {
	public final char[] cipherWord;
	private int numberOfDistinctCharacters;

	public Word(String cipherWord){
		this.cipherWord = cipherWord.toCharArray();

		numberOfDistinctCharacters = 1;

		for(int i = 1; i < this.cipherWord.length; i++){
			boolean duplicate = false;
			for(int j = 0; j < i; j++){
				if(this.cipherWord[i] == this.cipherWord[j]){
					duplicate = true;
				}
			}

			if(!duplicate){
				numberOfDistinctCharacters++;
			}
		}
	}

	public ValidStatus valid(Assignment a, Dictionary d){
		String decrypted = a.decrypt(cipherWord);
		if(decrypted != null){
			if(d.contains(decrypted)){
				return ValidStatus.VALID;
			}
			else{
				return ValidStatus.INVALID;
			}
		}

		return ValidStatus.UNDECIDED;
	}

	public int heuristic(){


		return numberOfDistinctCharacters;
	}

	public boolean contains(char letter){
		for(char c : cipherWord){
			if(c == letter){
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean equals(Object other){
		if(other instanceof Word){
			return ((Word)other).cipherWord == this.cipherWord;
		}

		return false;
	}
}
