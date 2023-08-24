import java.util.Scanner;

public class Utils {
    public static void printText(String text) {
        System.out.println(text);
    }

    public static int inputInt() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            input = scanner.nextInt();
        } catch (RuntimeException e) {
            Utils.printText("Необходимо вводить целые числа !\n");
        }

        return input;
    }
}
