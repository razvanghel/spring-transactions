package tests.dto;

import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDtoTests {

    @Test
    void testNoArgsConstructor() {
        TransactionDTO dto = new TransactionDTO();
        assertNull(dto.getId());
        assertNull(dto.getAccountId());
        assertNull(dto.getAmount());
        assertNull(dto.getTimestamp());
    }

    @Test
    void testSettersAndGetters() {
        TransactionDTO dto = new TransactionDTO();
        LocalDateTime now = LocalDateTime.now();
        dto.setId(1L);
        dto.setAccountId(1L);
        dto.setAmount(100.0);
        dto.setTimestamp(now);

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getAccountId());
        assertEquals(100.0, dto.getAmount());
        assertEquals(now, dto.getTimestamp());
    }
}
