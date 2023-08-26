import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionManager {
    public static void encrypt(boolean flag) {

        Path src = Path.of("resources/original.txt");
        Path dest = Path.of(flag ? "resources/encrypted.txt" : "resources/decrypted.txt");

        System.out.println("Введите ключ шифрования (целое число):");
        int key = Utils.inputInt();

        try (BufferedReader bufferedReader = Files.newBufferedReader(src, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(dest, StandardCharsets.UTF_8)) {
            while (bufferedReader.ready()) {
                String text = bufferedReader.readLine();
                bufferedWriter.write(flag ? CaesarCipher.encrypt(text, key) : CaesarCipher.decrypt(text, key));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время чтения/записи файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
