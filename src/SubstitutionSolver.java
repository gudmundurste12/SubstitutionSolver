import CSP.Dictionary;
import CSP.SubstitutionCSP;

import java.io.*;

/**
 * Created by Gvendurst on 9.3.2015.
 */
public class SubstitutionSolver {
	private static Dictionary dictionary;
	private static String dictionaryPath = "res/dictionaries";
	private static String messagePath = "res/messages";
	private static boolean findAll = false;

	private static String encryptedMessage;

	public static void main(String[] args){
		String userSpecified = "";

		if(args.length == 0){
			System.out.println("Need to provide file containing a encryptedMessage to be decrypted");
			System.exit(1);
		}

		retrieveMessage(messagePath + "/" + args[0]);

		System.out.println(encryptedMessage + "\r\n");

		if(args.length > 1){
			userSpecified = "/" + args[1];
		}

		dictionary = new Dictionary();
		populateDictionary(dictionaryPath + "/enable1.txt");
		populateDictionary(dictionaryPath + "/english2.txt");
		populateDictionary(dictionaryPath + "/english3.txt");
		addUserSpecifiedWords(dictionaryPath + "/userSpecified" + userSpecified);

		/*
		for(String s : encryptedMessage.split(" ")){
			if(dictionary.contains(s)){
				System.out.println("Dictionary contains " + s);
			}
			else{
				System.out.println("Dictionary does not contain " + s);
			}
		}
		*/


		long timeStarted = System.nanoTime();

		SubstitutionCSP csp = new SubstitutionCSP(dictionary, encryptedMessage);
		CSP.Assignment theAssignment = csp.solve(findAll);
		if(theAssignment != null) {
			long time = (System.nanoTime() - timeStarted) / 1000000000;
			System.out.println("Solution found in : " + time + " second(s)");

			System.out.println(theAssignment);

			StringBuilder build = new StringBuilder();

			for(char c : encryptedMessage.toCharArray()){
				build.append(theAssignment.decrypt(c));
			}

			System.out.println(build.toString());
		}
	}

	private static void retrieveMessage(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			StringBuilder builder = new StringBuilder();

			String line;
			boolean first = true;
			try{
				while ((line = br.readLine()) != null) {
					if(!first){
						builder.append("\r\n");
					}
					builder.append(line.toUpperCase());
					first = false;
				}

				encryptedMessage = builder.toString();
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
