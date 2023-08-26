import java.util.Scanner;

public class Utils {

    public static int inputInt() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            input = scanner.nextInt();
        } catch (RuntimeException e) {
            System.out.println(("Необходимо вводить целые числа!\n"));
        }

        return input;
    }
}
