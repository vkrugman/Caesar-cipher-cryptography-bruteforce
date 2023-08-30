import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static int inputInt() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Необходимо вводить только целые числа!");
            System.out.println("Допустимый диапазон: от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
        }
        return input;
    }
}
