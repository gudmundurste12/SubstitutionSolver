import java.io.*;
import java.util.HashSet;

/**
 * Created by Gvendurst on 9.3.2015.
 */
public class SubstitutionSolver {
	private static Dictionary dictionary;
	private static String dictionaryPath = "res/dictionaries";
	private static String messagePath = "res/messages";

	private static String message;

	public static void main(String[] args){
		String userSpecified = "";

		if(args.length == 0){
			System.out.println("Need to provide file containing a message to be decrypted");
			System.exit(1);
		}

		retrieveMessage(messagePath + "/" + args[0]);

		System.out.println(message);

		if(args.length > 1){
			userSpecified = "/" + args[1];
		}

		dictionary = new Dictionary();
		populateDictionary(dictionaryPath + "/enable1.txt");
		populateDictionary(dictionaryPath + "/english2.txt");
		populateDictionary(dictionaryPath + "/english3.txt");
		addUserSpecifiedWords(dictionaryPath + "/userSpecified" + userSpecified);

	}

	//TODO: Add real support for multiline text
	private static void retrieveMessage(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			StringBuilder builder = new StringBuilder();

			String line;
			try{
				while ((line = br.readLine()) != null) {
					builder.append(line.toUpperCase());
				}

				message = builder.toString();
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

	private static void populateDictionary(File file){
		System.out.println("Reading words from " + file.getName());
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			try{
				while ((line = br.readLine()) != null) {
					dictionary.add(line);
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

	private static void populateDictionary(String fileName){
		populateDictionary(new File(fileName));
	}

	private static void addUserSpecifiedWords(String dirName){
		File dir = new File(dirName);

		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File f : files){
				if(f.isFile()) {
					populateDictionary(f);
				}
			}
		}
		else{
			System.out.println(dirName + " is not a directory");
		}
	}
}
