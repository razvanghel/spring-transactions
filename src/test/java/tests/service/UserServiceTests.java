package tests.service;

import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.dto.AccountDTO;
import com.transactionsexample.spring_transactions.repository.UserRepository;
import com.transactionsexample.spring_transactions.service.AccountService;
import com.transactionsexample.spring_transactions.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountService accountService;

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

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(100.0);

        when(userRepository.findById(1L)).thenReturn(userDTO);
        when(accountService.getAccountsByCustomerId(1L)).thenReturn(Arrays.asList(accountDTO));

        UserDTO foundUser = userService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals("John", foundUser.getName());
        assertEquals("Doe", foundUser.getSurname());
        assertEquals(100.0, foundUser.getBalance());
    }

    @Test
    void getAllUsers() {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setName("John");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setName("Jane");

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(1L);
        accountDTO1.setCustomerId(1L);
        accountDTO1.setBalance(100.0);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(2L);
        accountDTO2.setCustomerId(2L);
        accountDTO2.setBalance(200.0);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(accountService.getAccountsByCustomerId(1L)).thenReturn(Arrays.asList(accountDTO1));
        when(accountService.getAccountsByCustomerId(2L)).thenReturn(Arrays.asList(accountDTO2));

        List<UserDTO> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals(100.0, users.get(0).getBalance());
        assertEquals(200.0, users.get(1).getBalance());
    }

    @Test
    void deleteUserById() {
        when(userRepository.existsById(1L)).thenReturn(true);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(100.0);

        when(accountService.getAccountsByCustomerId(1L)).thenReturn(Arrays.asList(accountDTO));

        userService.deleteUserById(1L);

        verify(accountService, times(1)).deleteAccountById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
