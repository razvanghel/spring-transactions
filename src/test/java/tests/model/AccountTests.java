package tests.model;

import com.transactionsexample.spring_transactions.model.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTests {

    @Test
    void testNoArgsConstructor() {
        Account account = new Account();
        assertNull(account.getId());
        assertNull(account.getCustomerId());
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testAllArgsConstructor() {
        Account account = new Account(1L);
        assertNull(account.getId());
        assertEquals(1L, account.getCustomerId());
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testSettersAndGetters() {
        Account account = new Account();

        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(100.0);

        assertEquals(1L, account.getId());
        assertEquals(1L, account.getCustomerId());
        assertEquals(100.0, account.getBalance());
    }
}
