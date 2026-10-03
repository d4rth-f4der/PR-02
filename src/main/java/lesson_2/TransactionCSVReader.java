package lesson_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TransactionCSVReader {
    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            InputStream inputStream;
            if (filePath.startsWith("http://") || filePath.startsWith("https://")) {
                inputStream = URI.create(filePath).toURL().openStream();
            } else {
                File file = new File(filePath);
                if (file.exists()) {
                    inputStream = new FileInputStream(file);
                } else {
                    InputStream resStream = getClass().getClassLoader().getResourceAsStream(filePath);
                    if (resStream != null) {
                        inputStream = resStream;
                    } else {
                        inputStream = new FileInputStream(filePath);
                    }
                }
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] values = line.split(",");
                    if (values.length >= 3) {
                        try {
                            double amount = Double.parseDouble(values[1].trim());
                            Transaction transaction = new Transaction(values[0].trim(), amount, values[2].trim());
                            transactions.add(transaction);
                        } catch (NumberFormatException ignored) {
                            // Пропускаємо рядок заголовка (наприклад, "Дата,Сума,Категорія")
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

