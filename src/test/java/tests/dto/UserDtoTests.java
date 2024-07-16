package tests.dto;

import com.transactionsexample.spring_transactions.dto.AccountDTO;
import com.transactionsexample.spring_transactions.dto.UserDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTests {

    @Test
    void testNoArgsConstructor() {
        UserDTO dto = new UserDTO();
        assertNull(dto.getId());
        assertNull(dto.getName());
        assertNull(dto.getSurname());
        assertNull(dto.getBalance());
        assertNull(dto.getAccountsResponse());
    }

    @Test
    void testSettersAndGetters() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("John");
        dto.setSurname("Doe");
        dto.setBalance(100.0);

        List<AccountDTO> accounts = List.of(new AccountDTO(1L, 1L, 100.0));
        dto.setAccountsResponse(accounts);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getName());
        assertEquals("Doe", dto.getSurname());
        assertEquals(100.0, dto.getBalance());
        assertEquals(accounts, dto.getAccountsResponse());
    }
}
