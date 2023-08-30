import java.nio.file.Path;

public class EncryptionManager {
    public static void encrypt(boolean flag) {
        Path src = Path.of(flag ? "resources/original.txt" : "resources/encrypted.txt");
        Path dest = Path.of(flag ? "resources/encrypted.txt" : "resources/decrypted.txt");

        System.out.println("Ваш выбор: " + (flag ? "\"Шифрование\"" : "\"Расшифровка\""));
        System.out.println("Введите ключ шифрования (целое число):");

        int key = Utils.inputInt();

        FileManager.save(new CaesarCipher(src, dest, flag, key));

        System.out.println("Исходный текст в файле: " + src.toAbsolutePath());
        System.out.println("Результат " + (flag ? "шифрования" : "расшифровки") + " в файле: " + dest.toAbsolutePath());
    }

    public static void bruteForce() {
        Path src = Path.of("resources/encrypted.txt");
        Path dest = Path.of("resources/decrypted.txt");

        System.out.println("Исходный (зашифрованный) текст в файле: " + src.toAbsolutePath());

        int key = CaesarCipher.getKey(src);
        CaesarCipher o = new CaesarCipher(src, dest, false, key);

        if (key != -1) {
            FileManager.save(o);
            System.out.println("Результат подбора ключа расшифровки (key = " + key + ") в файле: " + dest.toAbsolutePath());
        } else {
            System.out.println("""
                    ОГРАНИЧЕНИЕ: программа воспринимает знаки препинания как сигнал успешного подбора ключа (см. README).
                    В одной или нескольких строках оригинального (незашифрованный) текста отсутствуют знаки препинания.
                                
                    Таким образом, результат перебора всех вариантов ключей требует визуального анализа.
                    Воспользуйтесь п.2 Меню ("Расшифровка") и последовательно переберите ключи от 1 до """ + o.getAlphabetLength());
        }
    }
}
