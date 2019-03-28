import java.io.*;
import java.lang.StringBuffer;

public class caesarCracker {

    public static void main(String[] args) {

		try (BufferedReader encryptedWords = new BufferedReader(new FileReader("encryptedWords"));) {
			String encryptedWord;
			while ((encryptedWord = encryptedWords.readLine()) != null) {
				if (!crackencryptedWord(encryptedWord)) {
					System.out.println(encryptedWord + ": Not Decrypted");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean crackencryptedWord(String encryptedWord) {
		String check = encryptedWord;
		do {
			check = shift(check);
			if (compareToDictionary(check)) {
				System.out.println(encryptedWord + ": " + check);
				return true;
			}
		} while (!check.equals(encryptedWord));
		return false;
	}

	public static boolean compareToDictionary(String input) {
		try (BufferedReader dictionary = new BufferedReader(new FileReader("/usr/share/dict/words"));) {
			String currentDictionaryWord;
			while ((currentDictionaryWord = dictionary.readLine()) != null) {
				if (input.equals(currentDictionaryWord.toUpperCase())) {
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String shift(String input) {
		StringBuffer output = new StringBuffer();
		for (int currentChar = 0; currentChar < input.length(); currentChar++) {
			output.append((char)(((int)input.charAt(currentChar) + 1 - 65) % 26 + 65));
		}
		return output.toString();
	}
}
