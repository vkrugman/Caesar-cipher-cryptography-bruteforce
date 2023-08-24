public class Main {
    public static int shift = 3;

    public static final String MENU = """
            Выбери режим работы:
            1. Шифрование
            2. Расшифровка
            3. Криптоанализ методом brute force
            0. Выход""";

    public static void main(String[] args) {

        while (true) {
            Utils.printText(MENU);

            switch (Utils.inputInt()) {
                case 1 -> {
                    EncryptionManager.encrypt(true);
                }
                case 2 -> {
                    EncryptionManager.encrypt(false);
                }
                case 3 -> {
                }
                case 0 -> {
                    Utils.printText("Работа с программой завершена");
                    return;
                }
                default -> Utils.printText("Такой команды не существует!\n");
            }
        }

    }
}