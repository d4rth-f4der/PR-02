package lesson_2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/pr2.csv";
        TransactionCSVReader csvReader = new TransactionCSVReader();
        List<Transaction> transactions = csvReader.readTransactions(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
