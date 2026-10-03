package lesson_2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

    // Метод для виведення звіту про найбільшу і найменшу витрату за період
    public static void printExpenseRangeReport(String startDate, String endDate, Transaction maxExpense, Transaction minExpense) {
        System.out.println("\nАналіз витрат за період з " + startDate + " по " + endDate + ":");
        if (maxExpense != null) {
            System.out.println("  Найбільша витрата: " + maxExpense.getDescription() + " (" + maxExpense.getAmount() + " грн, дата: " + maxExpense.getDate() + ")");
        } else {
            System.out.println("  Найбільша витрата: немає даних");
        }
        if (minExpense != null) {
            System.out.println("  Найменша витрата: " + minExpense.getDescription() + " (" + minExpense.getAmount() + " грн, дата: " + minExpense.getDate() + ")");
        } else {
            System.out.println("  Найменша витрата: немає даних");
        }
    }

    // Метод для текстового візуального звіту витрат по категоріях
    public static void printExpensesByCategoryReport(List<Transaction> transactions, double unitScale) {
        System.out.println("\nВізуальний звіт: Сумарні витрати по категоріях (1 * = " + (int) unitScale + " грн):");
        Map<String, Double> expensesByCategory = new LinkedHashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + Math.abs(transaction.getAmount()));
            }
        }

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            double total = entry.getValue();
            int starsCount = total > 0 ? 1 + (int) (total / unitScale) : 0;
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.printf("%-22s: %8.2f грн | %s%n", entry.getKey(), total, stars);
        }
    }

    public static void printExpensesByCategoryReport(List<Transaction> transactions) {
        printExpensesByCategoryReport(transactions, 1000.0);
    }

    // Метод для текстового візуального звіту витрат по місяцях
    public static void printExpensesByMonthReport(List<Transaction> transactions, double unitScale) {
        System.out.println("\nВізуальний звіт: Сумарні витрати по місяцях (1 * = " + (int) unitScale + " грн):");
        Map<String, Double> expensesByMonth = new LinkedHashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                expensesByMonth.put(monthYear, expensesByMonth.getOrDefault(monthYear, 0.0) + Math.abs(transaction.getAmount()));
            }
        }

        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            double total = entry.getValue();
            int starsCount = total > 0 ? 1 + (int) (total / unitScale) : 0;
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.printf("%-10s: %8.2f грн | %s%n", entry.getKey(), total, stars);
        }
    }

    public static void printExpensesByMonthReport(List<Transaction> transactions) {
        printExpensesByMonthReport(transactions, 1000.0);
    }
}

