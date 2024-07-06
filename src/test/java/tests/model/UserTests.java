package tests.model;

import com.transactionsexample.spring_transactions.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    void testGettersAndSetters() {
        User user = new User();

        user.setId(1L);
        user.setName("John");
        user.setSurname("Doe");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
    }
}
