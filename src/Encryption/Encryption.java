package Encryption;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gvendurst on 4.4.2015.
 */
public class Encryption {
	private static String encryptionPath = "res/encryption/";

	public static void main(String[] args){
		if(args.length < 3){
			System.out.println("Need two parameters: Infile, cipherfile and outfile");
			System.exit(-1);
		}

		String message = readMessage(encryptionPath + args[0]);
		Map<Character, Character> cipher = getCipher(encryptionPath + args[1]);
		String encrypted = encryptMessage(message, cipher);
		writeToFile(encryptionPath + args[2], encrypted);
	}

	private static String readMessage(String fileName){
		StringBuilder builder = new StringBuilder();

		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line;
			try{
				while ((line = br.readLine()) != null) {
					builder.append(line.toLowerCase());
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

		return builder.toString();
	}

	private static Map<Character, Character> getCipher(String fileName){
		Map<Character, Character> returnValue = new HashMap<Character, Character>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line;
			int lineNumber = 0;
			try{
				while ((line = br.readLine()) != null) {
					lineNumber++;
					char[] chars = line.toCharArray();
					if(chars.length < 3){
						throw new IOException(fileName + "(" + lineNumber + ") Invalid line: \r\n" + line);
					}

					returnValue.put(chars[0], chars[2]);
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


		return returnValue;
	}

	private static String encryptMessage(String message, Map<Character, Character> cipher){
		StringBuilder builder = new StringBuilder();

		for(char c : message.toCharArray()){
			Character current = cipher.get(c);
			if(current == null){
				builder.append(c);
			}
			else{
				builder.append(current);
			}
		}

		return builder.toString();
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
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
