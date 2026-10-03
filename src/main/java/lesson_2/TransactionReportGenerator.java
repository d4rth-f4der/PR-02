package lesson_2;

import java.util.List;

public abstract class TransactionReportGenerator {

    // Метод для виведення звіту про загальний баланс
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    // Метод для виведення звіту про кількість транзакцій за місяць
    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    // Метод для виведення звіту про 10 найбільших витрат
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
}

