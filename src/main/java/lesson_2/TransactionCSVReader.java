package lesson_2;

import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    // Метод для парсингу рядка CSV та створення об'єкта Transaction
    public static Transaction parseLine(String line) {
        String[] values = line.split(",");
        return new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
    }

    // Метод для парсингу списку рядків CSV та створення списку об'єктів Transaction
    public static List<Transaction> parseLines(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            transactions.add(parseLine(line));
        }
        return transactions;
    }

    // Метод для читання та парсингу транзакцій з файлу CSV
    public static List<Transaction> readTransactions(String filePath) {
        List<String> lines = DataReader.readLines(filePath);
        return parseLines(lines);
    }
}
