import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionManager {
    public static void encrypt(boolean flag) { // todo the same method's name as CaesarCipher.encrypt

        Path src = Path.of("resources/original.txt");
        Path dest = Path.of(flag ? "resources/encrypted.txt" : "resources/decrypted.txt");

        System.out.println("Введите ключ шифрования (целое число):");
        int key = Utils.inputInt();

        try (BufferedReader bufferedReader = Files.newBufferedReader(src, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(dest, StandardCharsets.UTF_8)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                bufferedWriter.write(flag ? CaesarCipher.encrypt(line, key) : CaesarCipher.decrypt(line, key));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время чтения/записи файла!");
            throw new RuntimeException(e);
        }
    }

    public static void bruteForce() {
        Path src = Path.of("resources/encrypted.txt");
        Path dest = Path.of("resources/decrypted.txt");

        int key = CaesarCipher.keyCheck(src);

        try (BufferedReader bufferedReader = Files.newBufferedReader(src, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(dest, StandardCharsets.UTF_8)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                bufferedWriter.write(CaesarCipher.decrypt(line, key));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время чтения/записи файла!");
            throw new RuntimeException(e);
        }
    }

}
