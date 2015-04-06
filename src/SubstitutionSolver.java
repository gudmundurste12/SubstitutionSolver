import CSP.Dictionary;
import CSP.SubstitutionCSP;

import java.io.*;
import java.nio.file.*;

/**
 * Created by Gvendurst on 9.3.2015.
 */
public class SubstitutionSolver {
	private static Dictionary dictionary;
	private static String dictionaryPath = "res/dictionaries";
	private static String messagePath = "res/messages";
	private static String solutionPath = messagePath + "/solutions";
	private static boolean findAll = false;
	private static String messageName;

	private static String encryptedMessage;

	public static void main(String[] args){
		String userSpecified = "";

		if(args.length == 0){
			System.out.println("Need to provide file containing a encryptedMessage to be decrypted");
			System.exit(1);
		}

		messageName = args[0];

		retrieveMessage(messagePath + "/" + messageName);

		System.out.println(encryptedMessage + "\r\n");

		if(args.length > 1){
			for(int i = 1; i < args.length; i++){
				if(args[i].equals("-a")){
					findAll = true;
				}
				if(args[i].equals("-u")){
					boolean valid = true;
					if((i + 1) < args.length){
						if(!args[i + 1].startsWith("-")) {
							userSpecified = "/" + args[i + 1];
							i++;
						}
						else{
							valid = false;
						}
					}
					else{
						valid = false;
					}

					if(!valid){
						System.out.println("Must specify a parameter for -u");
						System.exit(1);
					}
				}
			}
		}

		dictionary = new Dictionary();
		populateDictionary(dictionaryPath + "/enable1.txt");
		populateDictionary(dictionaryPath + "/english2.txt");
		populateDictionary(dictionaryPath + "/english3.txt");
		if(!userSpecified.equals("")) {
			addUserSpecifiedWords(dictionaryPath + "/userSpecified" + userSpecified);
		}

		long timeStarted = System.nanoTime();

		SubstitutionCSP csp = new SubstitutionCSP(dictionary, encryptedMessage);
		CSP.Assignment theAssignment = csp.solve(findAll);
		if(theAssignment != null) {
			long time = (System.nanoTime() - timeStarted) / 1000000000;
			System.out.println("Solution found in : " + time + " second(s)");

			StringBuilder build = new StringBuilder();

			for(char c : encryptedMessage.toCharArray()){
				build.append(theAssignment.decrypt(c));
			}

			String decryptedMessage = build.toString();

			System.out.println(decryptedMessage);

			writeToFile(getFileName("_solved"), decryptedMessage);
			writeToFile(getFileName("_cipher_decryption"),  theAssignment.toString());
			writeToFile(getFileName("_cipher_encryption"),  theAssignment.inverseString());
		}
	}

	private static String getFileName(String addition){
		String path = solutionPath + "/" + messageName;

		String returnValue;

		if(hasFileExtention(path)){
			returnValue = path.substring(0, path.lastIndexOf('.'));
			String extension = path.substring(path.lastIndexOf('.'), path.length());
			returnValue = returnValue + addition + extension;
		}
		else{
			returnValue = path + addition;
		}

		return returnValue;
	}

	private static boolean hasFileExtention(String fileName){
		return fileName.matches(".*[.][a-z,A-Z]+");
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

	private static void writeToFile(String fileName, String message){
		try {
			File outFile = new File(fileName);
			if(outFile.exists()){
				outFile.delete();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

			bw.write(message);
			bw.flush();
			bw.close();
		}
		catch(IOException e){
			String newFileName = fileName.replace('/', '\\');
			newFileName = newFileName.substring(0, newFileName.lastIndexOf('\\'));
			File outFile = new File(newFileName);
			outFile.mkdir();
			System.out.println(e.getMessage());
			writeToFile(fileName, message);
		}
	}
}
