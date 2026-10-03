package lesson_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Виклик методу, який потрібно протестувати
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-12-2023", -100.0, "Кава"),
                new Transaction("02-12-2023", 5000.0, "Зарплата"),
                new Transaction("03-12-2023", -500.0, "Продукти"),
                new Transaction("04-12-2023", -2000.0, "Оренда"),
                new Transaction("05-12-2023", -300.0, "Аптека"),
                new Transaction("06-12-2023", -150.0, "Таксі"),
                new Transaction("07-12-2023", -800.0, "Одяг"),
                new Transaction("08-12-2023", -50.0, "Метро"),
                new Transaction("09-12-2023", -1200.0, "Техніка"),
                new Transaction("10-12-2023", -400.0, "Книги"),
                new Transaction("11-12-2023", -250.0, "Спортзал"),
                new Transaction("12-12-2023", -900.0, "Комунальні")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Має повертатися рівно 10 найбільших витрат");
        Assertions.assertEquals(-2000.0, topExpenses.get(0).getAmount(), "Перша витрата має бути найбільшою (-2000)");
        Assertions.assertEquals(-1200.0, topExpenses.get(1).getAmount(), "Друга витрата має бути -1200");
        Assertions.assertEquals(-900.0, topExpenses.get(2).getAmount(), "Третя витрата має бути -900");
        Assertions.assertEquals(-100.0, topExpenses.get(9).getAmount(), "Остання в топ-10 має бути -100");
    }

    @Test
    public void testFindMaxAndMinExpenseInRange() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-12-2023", -450.0, "Сільпо"),
                new Transaction("05-12-2023", -120.0, "Аптека"),
                new Transaction("10-12-2023", 8000.0, "Зарплата"),
                new Transaction("15-12-2023", -3200.0, "Оренда"),
                new Transaction("31-12-2023", -750.0, "Подарунки"),
                new Transaction("05-01-2024", -5000.0, "Подорож (поза періодом)")
        );

        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseInRange(transactions, "01-12-2023", "31-12-2023");
        Transaction minExpense = TransactionAnalyzer.findMinExpenseInRange(transactions, "01-12-2023", "31-12-2023");

        Assertions.assertNotNull(maxExpense, "Найбільша витрата не повинна бути null");
        Assertions.assertEquals(-3200.0, maxExpense.getAmount(), "Найбільша витрата в грудні має бути -3200.0");
        Assertions.assertEquals("Оренда", maxExpense.getDescription());

        Assertions.assertNotNull(minExpense, "Найменша витрата не повинна бути null");
        Assertions.assertEquals(-120.0, minExpense.getAmount(), "Найменша витрата в грудні має бути -120.0");
        Assertions.assertEquals("Аптека", minExpense.getDescription());
    }
}
