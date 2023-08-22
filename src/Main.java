import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int shift = 3;

    public static final String ORIGINAL_TEXT = "Сделай шаг и дорога появится!";
    public static final String CIPHER_TEXT = "Фзиогм ыгж л зсусжг тсвелхфв!";

    public static final String MENU = """
            Выбери режим работы:
            1. Шифрование
            2. Расшифровка
            3. Криптоанализ методом brute force
            0. Выход""";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                printText(MENU);

                switch (scanner.nextInt()) {
                    case 1 -> {
                        String cipher = CaesarCipher.cipher(ORIGINAL_TEXT, shift);
                        System.out.println(cipher);
                        System.out.println(CIPHER_TEXT.equals(cipher));
                    }
                    case 2 -> {
                        System.out.println(CaesarCipher.decipher(CIPHER_TEXT, shift));
                    }
                    case 3 -> {}
                    case 0 -> {
                        printText("Работа с программой завершена!");
                        return;
                    }
                    default -> printText("Такой команды не существует!\n");
                }
            }
        } catch (InputMismatchException exception) {
            printText("Необходимо вводить целые числа !\n");
        }
    }

    public static void printText(String text) {
        System.out.println(text);
    }
}