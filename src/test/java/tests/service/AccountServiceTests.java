package tests.service;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.repository_impl.AccountRepositoryImpl;
import com.transactionsexample.spring_transactions.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTests {

    @Mock
    private AccountRepositoryImpl accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setCustomerId(1L);
        accountRequestDTO.setCustomerId(1L);
        accountRequestDTO.setBalance(100.0);

        accountService.createAccount(accountRequestDTO);
        verify(accountRepository, times(1)).save(accountRequestDTO);
    }

    @Test
    void getAccountById() {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(1L);
        accountResponseDTO.setCustomerId(1L);
        accountResponseDTO.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(accountResponseDTO);

        AccountResponseDTO foundAccount = accountService.getAccountById(1L);
        assertNotNull(foundAccount);
        assertEquals(100.0, foundAccount.getBalance());
        assertEquals(1L, foundAccount.getCustomerId());
    }

    @Test
    void getAccountsByCustomerId() {
        AccountResponseDTO account1 = new AccountResponseDTO();
        account1.setId(1L);
        account1.setCustomerId(1L);
        account1.setBalance(100.0);

        AccountResponseDTO account2 = new AccountResponseDTO();
        account2.setId(2L);
        account2.setCustomerId(1L);
        account2.setBalance(200.0);

        when(accountRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(account1, account2));

        List<AccountResponseDTO> accounts = accountService.getAccountsByCustomerId(1L);
        assertEquals(2, accounts.size());
    }

    @Test
    void getAllAccounts() {
        AccountResponseDTO account1 = new AccountResponseDTO();
        account1.setId(1L);
        account1.setCustomerId(1L);
        account1.setBalance(100.0);

        AccountResponseDTO account2 = new AccountResponseDTO();
        account2.setId(2L);
        account2.setCustomerId(2L);
        account2.setBalance(200.0);

        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    void deleteAccountById() {
        accountService.deleteAccountById(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }
}
