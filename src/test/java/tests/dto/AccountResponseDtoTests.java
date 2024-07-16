package tests.dto;

import com.transactionsexample.spring_transactions.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountResponseDtoTests {

    @Test
    void testNoArgsConstructor() {
        AccountDTO dto = new AccountDTO();
        assertNull(dto.getId());
        assertNull(dto.getCustomerId());
        assertNull(dto.getBalance());
    }

    @Test
    void testAllArgsConstructor() {
        AccountDTO dto = new AccountDTO(1L, 1L, 100.0);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getCustomerId());
        assertEquals(100.0, dto.getBalance());
    }

    @Test
    void testSettersAndGetters() {
        AccountDTO dto = new AccountDTO();
        dto.setId(1L);
        dto.setCustomerId(1L);
        dto.setBalance(100.0);

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getCustomerId());
        assertEquals(100.0, dto.getBalance());
    }
}
