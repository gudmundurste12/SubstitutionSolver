import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Gvendurst on 9.3.2015.
 */
public class SubstitutionSolver {
	private static HashSet<String> dictionary;

	public static void main(String[] args){
		dictionary = new HashSet<String>();
		populateDictionary("res/english3.txt");


	}

	private static void populateDictionary(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line;
			try{
				while ((line = br.readLine()) != null) {
					dictionary.add(line.toLowerCase());
				}

			}
			catch(IOException e){
				System.out.println(e.getMessage());
				System.exit(1);
			}

			br.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
