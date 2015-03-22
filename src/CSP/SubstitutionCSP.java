package CSP;

/**
 * Created by Gvendurst on 22.3.2015.
 */
public class SubstitutionCSP {
	private Dictionary dictionary;
	private String encryptedMessage;

	public SubstitutionCSP(Dictionary dictionary, String encryptedMessage){
		this.dictionary = dictionary;
		this.encryptedMessage = encryptedMessage;
	}

	public Assignment solve(){

		return CSP_Backtracking(new Assignment());
	}

	public Assignment CSP_Backtracking(Assignment a){
		if(a.complete()){
			return a;
		}



		return null;
	}
}
