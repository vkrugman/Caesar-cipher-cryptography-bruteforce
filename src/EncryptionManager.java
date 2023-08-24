import java.io.*;

public class EncryptionManager {
    public static void encrypt(boolean flag) {
        String src = "resources/original.txt";
        String dest = flag ? "resources/encrypted.txt" : "resources/decrypted.txt";

        Utils.printText("Введите ключ шифрования (целое число):");
        int key = Utils.inputInt();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest))) {

            while (bufferedReader.ready()) {
                String text = bufferedReader.readLine();
                bufferedWriter.write(flag ? CaesarCipher.cipher(text, key) : CaesarCipher.decipher(text, key));
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
