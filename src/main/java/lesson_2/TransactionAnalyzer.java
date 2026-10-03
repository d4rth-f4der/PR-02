package lesson_2;

// import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAnalyzer {
    private List<Transaction> transactions;
    // private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Метод для розрахунку загального балансу
    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Тут будуть інші методи для аналізу транзакцій
}


