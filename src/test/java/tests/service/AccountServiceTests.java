package tests.service;

import com.transactionsexample.spring_transactions.dto.AccountDTO;
import com.transactionsexample.spring_transactions.repository_impl.AccountRepositoryImpl;
import com.transactionsexample.spring_transactions.service.AccountService;
import com.transactionsexample.spring_transactions.service.TransactionService;
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

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
        AccountDTO accountRequestDTO = new AccountDTO();
        accountRequestDTO.setCustomerId(1L);
        accountRequestDTO.setCustomerId(1L);
        accountRequestDTO.setBalance(0.0);

        accountService.createAccount(accountRequestDTO);
        verify(accountRepository, times(1)).save(accountRequestDTO);
    }

    @Test
    void getAccountById() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(0.0);

        when(accountRepository.findById(1L)).thenReturn(accountDTO);

        AccountDTO foundAccount = accountService.getAccountById(1L);
        assertNotNull(foundAccount);
        assertEquals(0.0, foundAccount.getBalance());
        assertEquals(1L, foundAccount.getCustomerId());
    }

    @Test
    void getAccountsByCustomerId() {
        AccountDTO account1 = new AccountDTO();
        account1.setId(1L);
        account1.setCustomerId(1L);
        account1.setBalance(100.0);

        AccountDTO account2 = new AccountDTO();
        account2.setId(2L);
        account2.setCustomerId(1L);
        account2.setBalance(200.0);

        when(accountRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(account1, account2));

        List<AccountDTO> accounts = accountService.getAccountsByCustomerId(1L);
        assertEquals(2, accounts.size());
    }

    @Test
    void getAllAccounts() {
        AccountDTO account1 = new AccountDTO();
        account1.setId(1L);
        account1.setCustomerId(1L);
        account1.setBalance(100.0);

        AccountDTO account2 = new AccountDTO();
        account2.setId(2L);
        account2.setCustomerId(2L);
        account2.setBalance(200.0);

        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

        List<AccountDTO> accounts = accountService.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    void deleteAccountById() {
        when(accountRepository.existsById(1L)).thenReturn(true);

        accountService.deleteAccountById(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }
}
