public class CaesarCipher {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + ".,”:-!? ";

    public static String cipher(String text, int shift) {

        StringBuilder result = new StringBuilder();

        for (char symbol : text.toCharArray()) {
            int originalAlphabetPosition = ALPHABET.indexOf(symbol);

            if (originalAlphabetPosition >= 0) {
                int newAlphabetPosition = (originalAlphabetPosition + shift) % ALPHABET.length();
                char newChar = ALPHABET.charAt(newAlphabetPosition);
                result.append(newChar);
            } else {
                result.append(symbol);
            }
        }

        return result.toString();
    }

    public static String decipher(String text, int shift) {
        if (shift == 0) {
            return text;
        }

        shift = shift > 0 ? (ALPHABET.length() - (shift % ALPHABET.length())) : (-shift % ALPHABET.length());
        return cipher(text, shift);
    }
}
