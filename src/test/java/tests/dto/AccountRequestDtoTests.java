package tests.dto;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountRequestDtoTests {

    @Test
    void testNoArgsConstructor() {
        AccountRequestDTO dto = new AccountRequestDTO();
        assertNull(dto.getCustomerId());
        assertNull(dto.getBalance());
    }

    @Test
    void testAllArgsConstructor() {
        AccountRequestDTO dto = new AccountRequestDTO(1L, 100.0);
        assertEquals(1L, dto.getCustomerId());
        assertEquals(100.0, dto.getBalance());
    }

    @Test
    void testSettersAndGetters() {
        AccountRequestDTO dto = new AccountRequestDTO();
        dto.setCustomerId(1L);
        dto.setBalance(100.0);

        assertEquals(1L, dto.getCustomerId());
        assertEquals(100.0, dto.getBalance());
    }
}
