import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileManager {
    public static void save(CaesarCipher o) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(o.getSrc(), StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(o.getDest(), StandardCharsets.UTF_8)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                bufferedWriter.write(o.isEncrypt() ? CaesarCipher.encrypt(line, o.getKey()) : CaesarCipher.decrypt(line, o.getKey()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время чтения/записи файла!");
            throw new RuntimeException(e);
        }
    }
}
