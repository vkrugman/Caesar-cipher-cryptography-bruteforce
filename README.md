Криптоанализ с использованием шифра Цезаря
=========
Итоговый проект к модулю [Java Syntax](https://javarush.com/quests/QUEST_JAVA_SYNTAX) университета JavaRush. 

Техническое задание
-----------------
```
Задача: написать программу, которая работает с шифром Цезаря.

За основу криптографического алфавита возьми все буквы русского алфавита и знаки пунктуации (. , ”” : - ! ? ПРОБЕЛ). 
Если попадаются символы, которые не входят в наш криптографический алфавит, просто пропусти их.

Обязательные требования
У программы должно быть 2 режима:

1. Шифрование / расшифровка. 
Программа должна зашифровывать и расшифровывать текст, используя заданный криптографический ключ.
Программа должна получать путь к текстовому файлу с исходным текстом и на его основе создавать файл с зашифрованным текстом.

2. Криптоанализ методом brute force
Программа должна взламывать зашифрованный текст, переданный в виде текстового файла.
Если пользователь выбирает brute force (брутфорс, поиск грубой силой), программа должна самостоятельно, путем перебора, 
подобрать ключ и расшифровать текст.

Подумай, какой критерий программа должна воспринимать как сигнал успешного подбора ключа. Возможно, 
нужно обратить внимание на пробелы между словами или правильность использования знаков пунктуации.
```
Функционал
-----------------
- `Интерфейс` - меню с командами ввода в консоли приложения
- `Шифрование` - поддерживает положительный и отрицательный ключи
- `Расшифровка` - поддерживает положительный и отрицательный ключи
- `Brute force` - программа самостоятельно подбирает ключ

- `Работа с файлами` - исходный текст и результат шифрования/расшифровки сохраняются в отдельных файлах. 
После успешного выполнения каждой операции программа предоставляет ссылку для открытия файлов 
(исходный и с результатом выполнения операции)

Описание алгоритма Brute force 
-----------------
- Программа анализирует все строки текста на наличие знаков пунктуации
- Приоритетным критерием является проверка на наличие знаков препинания в конце каждой строки:
  `".", ",", ":", "!", "?"`
- Вторым критерием является проверка на наличие следующих знаков препинания внутри каждой строки:
  `". ", ", ", ": ", " - ", "! ", "? "`
- Как только программа находит знак препинания в конце или внутри каждой строки, она переходит к анализу следующей строки.
- Ключ шифрования считается успешно подобранным в случае, когда после расшифровки количество строк текста 
совпадает с количеством сработавших критериев.

Ограничения
-----------------
- В соответствии с ТЗ используется криптографический алфавит из букв русского алфавита и знаков пунктуации:
  `АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!?ПРОБЕЛ`
- Минимальный и максимальный ключи шифрования ограничены `Integer.MAX_VALUE` и `Integer.MAX_VALUE` соответственно
- Исходник текста и результаты шифрования/расшифровки/подбора ключа методом brute force сохраняются в папке проекта `resources`.