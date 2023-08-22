public class CaesarCipher {
    private static final int ALPHABET_SIZE = 32;

    public static String cipher(String text, int shift) {

        StringBuilder result = new StringBuilder();

        // todo обработать отрицательное смешение при шифровании и дешифровании
        // todo обработать смещение относительно ё/Ё
        // todo обработать символы . , ”” : - ! ? ПРОБЕЛ

        for (char symbol : text.toCharArray()) {
            if (Character.isLetter(symbol)) {
                char letterA = Character.isLowerCase(symbol) ? 'а' : 'А';
                int originalAlphabetPosition = symbol - letterA;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % ALPHABET_SIZE;
                char newChar = (char) (letterA + newAlphabetPosition);
                result.append(newChar);
            } else {
                result.append(symbol);
            }
        }

        return result.toString();
    }

    public static String decipher(String text, int shift) {
        return cipher(text, ALPHABET_SIZE - (shift % ALPHABET_SIZE));
    }
}
