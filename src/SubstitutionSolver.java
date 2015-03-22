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
	private static AssignmentSet caesar3;

	private static String encryptedMessage;

	public static void main(String[] args){
		String userSpecified = "";

		if(args.length == 0){
			System.out.println("Need to provide file containing a encryptedMessage to be decrypted");
			System.exit(1);
		}

		retrieveMessage(messagePath + "/" + args[0]);

		System.out.println(encryptedMessage);

		if(args.length > 1){
			userSpecified = "/" + args[1];
		}

		dictionary = new Dictionary();
		populateDictionary(dictionaryPath + "/enable1.txt");
		populateDictionary(dictionaryPath + "/english2.txt");
		populateDictionary(dictionaryPath + "/english3.txt");
		addUserSpecifiedWords(dictionaryPath + "/userSpecified" + userSpecified);

		SubstitutionCSP csp = new SubstitutionCSP(dictionary, encryptedMessage);
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

	private static void testAssignmentSet(){
		caesar3 = new AssignmentSet();
		caesar3.addAssignment(new Assignment('D','a'));
		caesar3.addAssignment(new Assignment('E','b'));
		caesar3.addAssignment(new Assignment('F','c'));
		caesar3.addAssignment(new Assignment('G','d'));
		caesar3.addAssignment(new Assignment('H','e'));
		caesar3.addAssignment(new Assignment('I','f'));
		caesar3.addAssignment(new Assignment('J','g'));
		caesar3.addAssignment(new Assignment('K','h'));
		caesar3.addAssignment(new Assignment('L','i'));
		caesar3.addAssignment(new Assignment('M','j'));
		caesar3.addAssignment(new Assignment('N','k'));
		caesar3.addAssignment(new Assignment('O','l'));
		caesar3.addAssignment(new Assignment('P','m'));
		caesar3.addAssignment(new Assignment('Q','n'));
		caesar3.addAssignment(new Assignment('R','o'));
		caesar3.addAssignment(new Assignment('S','p'));
		caesar3.addAssignment(new Assignment('T','q'));
		caesar3.addAssignment(new Assignment('U','r'));
		caesar3.addAssignment(new Assignment('V','s'));
		caesar3.addAssignment(new Assignment('W','t'));
		caesar3.addAssignment(new Assignment('X','u'));
		caesar3.addAssignment(new Assignment('Y','v'));
		caesar3.addAssignment(new Assignment('Z','w'));
		caesar3.addAssignment(new Assignment('A','x'));
		caesar3.addAssignment(new Assignment('B','y'));
		caesar3.addAssignment(new Assignment('C','z'));

		for(String s : encryptedMessage.split(" ")){
			try{
				System.out.print(caesar3.decryptWord(s) + " ");
			}
			catch(AssignmentSetException e){
				System.out.println("\n" + e.getMessage());
			}
		}
	}
}
