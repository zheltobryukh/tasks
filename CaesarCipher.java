import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CaesarCipher {
    private static final String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nВыберите режим работы:");
            System.out.println("1. Зашифровать текст");
            System.out.println("2. Расшифровать текст (с помощью ключа)");
            System.out.println("3. Расшифровать текст (brute force)");
            System.out.println("4. Выход");

            int choice = getValidChoice(1, 4);

            if (choice == 4) {
                System.out.println("Программа завершена.");
                break;
            }

            processChoice(choice);
        }
    }

    private static void processChoice(int choice) {
        try {
            System.out.println("Введите путь к входному файлу:");
            String inputPath = scanner.nextLine();
            validateInputFile(inputPath);

            System.out.println("Введите путь к выходному файлу:");
            String outputPath = scanner.nextLine();

            switch (choice) {
                case 1:
                case 2:
                    System.out.println("Введите ключ шифрования (сдвиг):");
                    int key = getValidKey();
                    processFile(inputPath, outputPath, key, choice == 1);
                    break;
                case 3:
                    bruteForceDecode(inputPath, outputPath);
                    break;
            }
            System.out.println("Операция успешно завершена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void processFile(String inputPath, String outputPath, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processed = processText(line, key, encrypt);
                writer.write(processed);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    private static String processText(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            int index = RUSSIAN_ALPHABET.indexOf(c);
            if (index != -1) {
                int shift = encrypt ? key : -key;
                int newIndex = Math.floorMod(index + shift, RUSSIAN_ALPHABET.length());
                result.append(RUSSIAN_ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static void bruteForceDecode(String inputPath, String outputPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputPath)));
            for (int key = 0; key < RUSSIAN_ALPHABET.length(); key++) {
                String decoded = processText(content, key, false);
                if (containsRussianWords(decoded)) {
                    Files.write(Paths.get(outputPath), decoded.getBytes());
                    System.out.println("Предполагаемый ключ шифрования: " + key);
                    return;
                }
            }
            throw new RuntimeException("Не удалось подобрать ключ");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при брутфорс расшифровке: " + e.getMessage());
        }
    }

    private static boolean containsRussianWords(String text) {
        // Простая проверка на наличие частых русских слов
        String[] commonWords = {"И", "В", "НА", "ЧТО", "ЭТО"};
        text = text.toUpperCase();
        for (String word : commonWords) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private static void validateInputFile(String path) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("Входной файл не существует");
        }
    }

    private static int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Пожалуйста, введите число от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число");
            }
        }
    }

    private static int getValidKey() {
        while (true) {
            try {
                int key = Integer.parseInt(scanner.nextLine());
                return Math.floorMod(key, RUSSIAN_ALPHABET.length());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число");
            }
        }
    }
}
