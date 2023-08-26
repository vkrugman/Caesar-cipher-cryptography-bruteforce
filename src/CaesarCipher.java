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
            int newAlphabetPosition = -1;

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
}
