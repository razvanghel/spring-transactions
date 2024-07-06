package tests.service;

import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.repository.UserRepository;
import com.transactionsexample.spring_transactions.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John");
        userDTO.setSurname("Doe");

        userService.createUser(userDTO);
        verify(userRepository, times(1)).save(userDTO);
    }

    @Test
    void getUserById() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John");
        userDTO.setSurname("Doe");

        when(userRepository.findById(1L)).thenReturn(userDTO);

        UserDTO foundUser = userService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals("John", foundUser.getName());
        assertEquals("Doe", foundUser.getSurname());
    }

    @Test
    void getAllUsers() {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setName("John");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setName("Jane");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
