package CSP;

/**
 * Created by Gvendurst on 15.3.2015.
 */
public class Availability {
	public boolean available;
	public Character deletedBy;

	public Availability(){
		available = true;
		deletedBy = null;
	}

	public Availability(boolean available){
		this.available = available;
	}
}
