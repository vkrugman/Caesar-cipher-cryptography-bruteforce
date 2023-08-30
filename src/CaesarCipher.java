import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CaesarCipher {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + ".,”:-!? ";
    private final Path src;
    private final Path dest;
    private final boolean encrypt;
    private final int key;

    public CaesarCipher(Path src, Path dest, boolean encrypt, int key) {
        this.src = src;
        this.dest = dest;
        this.encrypt = encrypt;
        this.key = key;
    }

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

                    shift = shift % ALPHABET.length();

                    newAlphabetPosition = originalAlphabetPosition >= shift % ALPHABET.length()
                            ? (originalAlphabetPosition - shift)
                            : ALPHABET.length() - (shift - originalAlphabetPosition);
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

    public static int getKey(Path src) {
        int key = -1;

        for (int i = 1; i <= ALPHABET.length(); i++) {
            int countStr = 0;
            int endStrPunctuation = 0;
            int innerStrPunctuation = 0;

            try (BufferedReader bufferedReader = Files.newBufferedReader(src)) {

                while (bufferedReader.ready() && (checkFrequencyPunctuation(countStr, endStrPunctuation, innerStrPunctuation))) {
                    String line = bufferedReader.readLine();

                    if (line.isBlank()) {
                        continue;
                    }

                    String decryptedStr = decrypt(line, i);
                    countStr++;

                    if (isEndStrPunctuation(decryptedStr)) {
                        endStrPunctuation++;
                    } else if (isInnerStrPunctuation(decryptedStr)) {
                        innerStrPunctuation++;
                    }
                }

                if (checkFrequencyPunctuation(countStr, endStrPunctuation, innerStrPunctuation)) {
                    key = i;
                    i = ALPHABET.length();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return key;
    }

    public static boolean isEndStrPunctuation(String text) {
        List<String> keys = List.of(".", ",", ":", "!", "?");
        boolean isEnds = false;

        for (String key : keys) {
            isEnds = text.lastIndexOf(key) == text.length() - 1;

            if (isEnds) {
                break;
            }
        }
        return isEnds;
    }

    public static boolean isInnerStrPunctuation(String text) {
        List<String> keys = List.of(". ", ", ", ": ", " - ", "! ", "? ");
        boolean isContain = false;

        for (String key : keys) {
            isContain = text.contains(key);

            if (isContain) {
                break;
            }
        }
        return isContain;
    }

    public static boolean checkFrequencyPunctuation(int strCount, int innerPunctuation, int endPunctuation) {
        if (strCount != (innerPunctuation + endPunctuation)) {
            return false;
        }
        return strCount == endPunctuation + innerPunctuation;
    }

    public Path getSrc() {
        return src;
    }

    public Path getDest() {
        return dest;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public int getKey() {
        return key;
    }

    public int getAlphabetLength() {
        return ALPHABET.length();
    }
}
