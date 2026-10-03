package lesson_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testParseLine() {
        String line = "05-12-2023,-450,Сільпо";
        Transaction transaction = TransactionCSVReader.parseLine(line);

        Assertions.assertNotNull(transaction);
        Assertions.assertEquals("05-12-2023", transaction.getDate());
        Assertions.assertEquals(-450.0, transaction.getAmount());
        Assertions.assertEquals("Сільпо", transaction.getDescription());
    }

    @Test
    public void testParseLines() {
        List<String> lines = Arrays.asList(
                "05-12-2023,-450,Сільпо",
                "",
                "   ",
                "10-12-2023,8000,Зарплата",
                "14-12-2023,-3200,Оренда квартири"
        );

        List<Transaction> transactions = TransactionCSVReader.parseLines(lines);

        Assertions.assertEquals(3, transactions.size(), "Порожні рядки повинні ігноруватися");
        Assertions.assertEquals("05-12-2023", transactions.get(0).getDate());
        Assertions.assertEquals(-450.0, transactions.get(0).getAmount());
        Assertions.assertEquals("10-12-2023", transactions.get(1).getDate());
        Assertions.assertEquals(8000.0, transactions.get(1).getAmount());
        Assertions.assertEquals("14-12-2023", transactions.get(2).getDate());
        Assertions.assertEquals(-3200.0, transactions.get(2).getAmount());
    }
}
