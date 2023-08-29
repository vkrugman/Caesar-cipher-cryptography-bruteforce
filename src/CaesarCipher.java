import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CaesarCipher {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + ".,”:-!? ";

    public static String encrypt(String text, int offset) {
        if (offset == 0) {
            return text;
        }

        StringBuilder result = new StringBuilder();

        for (char symbol : text.toCharArray()) {
            int originalAlphabetPosition = ALPHABET.indexOf(symbol);
            int newAlphabetPosition;

            if (originalAlphabetPosition >= 0) {
                if (offset > 0) {
                    newAlphabetPosition = (originalAlphabetPosition + offset) % ALPHABET.length();
                    result.append(ALPHABET.charAt(newAlphabetPosition));
                } else {
                    int shift = -offset;
                    newAlphabetPosition = originalAlphabetPosition >= shift
                            ? (originalAlphabetPosition - shift)
                            : ALPHABET.length() - (shift - originalAlphabetPosition) % ALPHABET.length();
                    result.append(ALPHABET.charAt(newAlphabetPosition));
                }
            } else {
                result.append(symbol);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int offset) {
        if (offset == 0) {
            return text;
        }

        offset = offset > 0 ? (ALPHABET.length() - (offset % ALPHABET.length())) : (-offset % ALPHABET.length());
        return encrypt(text, offset);
    }

    public static int keyCheck(Path src) {
        int key = Integer.MIN_VALUE;

        for (int i = 1; i <= ALPHABET.length(); i++) { //todo negative key
            try (BufferedReader bufferedReader = Files.newBufferedReader(src)) {
                while (bufferedReader.ready()) {
                    String decryptedStr = decrypt(bufferedReader.readLine(), i);

                    if (isCorrectPunctuation(decryptedStr)) {
                        key = i;
                        i = ALPHABET.length();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return key;
    }

    public static boolean isCorrectPunctuation(String text) { //todo private
        List<String> keys = List.of(". ", ", ", " ”", "” ", ": ", " - ", "! ", "? ");
        boolean isCorrect = false;

        for (String key : keys) {
            isCorrect = text.contains(key);

            if (isCorrect) {
                break;
            }
        }
        return isCorrect;
    }
}
