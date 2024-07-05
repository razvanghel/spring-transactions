package tests.repository_impl;

import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.repository_impl.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTests {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    void testSave() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("John");
        dto.setSurname("Doe");

        userRepository.save(dto);
        UserDTO user = userRepository.findById(1L);
        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
    }

    @Test
    void testFindById() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("John");
        dto.setSurname("Doe");

        userRepository.save(dto);
        UserDTO user = userRepository.findById(1L);
        assertNotNull(user);
        assertEquals(1L, user.getId());
    }

    @Test
    void testFindAll() {
        UserDTO dto1 = new UserDTO();
        dto1.setId(1L);
        dto1.setName("John");
        dto1.setSurname("Doe");

        UserDTO dto2 = new UserDTO();
        dto2.setId(2L);
        dto2.setName("Jane");
        dto2.setSurname("Doe");

        userRepository.save(dto1);
        userRepository.save(dto2);

        List<UserDTO> users = userRepository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void testDeleteById() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("John");
        dto.setSurname("Doe");

        userRepository.save(dto);
        userRepository.deleteById(1L);
        UserDTO user = userRepository.findById(1L);
        assertNull(user);
    }
}
