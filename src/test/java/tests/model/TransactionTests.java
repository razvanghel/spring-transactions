package tests.model;

import com.transactionsexample.spring_transactions.model.Transaction;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTests {

    @Test
    void testGettersAndSetters() {
        Transaction transaction = new Transaction();
        LocalDateTime now = LocalDateTime.now();

        transaction.setId(1L);
        transaction.setAccountId(1L);
        transaction.setAmount(100.0);
        transaction.setTimestamp(now);

        assertEquals(1L, transaction.getId());
        assertEquals(1L, transaction.getAccountId());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(now, transaction.getTimestamp());
    }
}
