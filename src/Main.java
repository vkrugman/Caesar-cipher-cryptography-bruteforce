public class Main {
    public static final String MENU = """
            Выбери режим работы:
            1. Шифрование
            2. Расшифровка
            3. Криптоанализ методом brute force
            0. Выход""";

    public static void main(String[] args) {
        EncryptionManager manager = new EncryptionManager();

        while (true) {
            System.out.println(MENU);

            switch (Utils.inputInt()) {
                case 1 -> EncryptionManager.encrypt(true);
                case 2 -> EncryptionManager.encrypt(false);
                case 3 -> EncryptionManager.bruteForce();
                case 0 -> {
                    System.out.println("Работа с программой завершена");
                    return;
                }
                default -> System.out.println("Такой команды не существует!\n");
            }
        }
    }
}