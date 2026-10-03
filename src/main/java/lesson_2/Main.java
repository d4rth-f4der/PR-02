package lesson_2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/pr2.csv";
        List<String> lines = DataReader.readLines(filePath);
        List<Transaction> transactions = TransactionCSVReader.parseLines(lines);

        for (Transaction transaction : transactions) {System.out.println(transaction);}

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseInRange(transactions, startDate, endDate);
        Transaction minExpense = TransactionAnalyzer.findMinExpenseInRange(transactions, startDate, endDate);
        TransactionReportGenerator.printExpenseRangeReport(startDate, endDate, maxExpense, minExpense);

        TransactionReportGenerator.printExpensesByCategoryReport(transactions, 500.0);
        TransactionReportGenerator.printExpensesByMonthReport(transactions, 1000.0);
    }
}

