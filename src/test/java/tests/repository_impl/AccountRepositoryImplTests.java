package tests.repository_impl;

import com.transactionsexample.spring_transactions.dto.AccountDTO;
import com.transactionsexample.spring_transactions.repository_impl.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTests {

    private AccountRepositoryImpl accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepositoryImpl();
    }

    @Test
    void testSave() {
        AccountDTO dto = new AccountDTO(1L, 1L, 100.0);
        accountRepository.save(dto);
        AccountDTO account = accountRepository.findById(1L);
        assertNotNull(account);
        assertEquals(1L, account.getCustomerId());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testFindById() {
        AccountDTO dto = new AccountDTO(1L, 1L, 100.0);
        accountRepository.save(dto);
        AccountDTO account = accountRepository.findById(1L);
        assertNotNull(account);
        assertEquals(1L, account.getId());
    }

    @Test
    void testFindByCustomerId() {
        AccountDTO dto1 = new AccountDTO(1L, 1L, 100.0);
        AccountDTO dto2 = new AccountDTO(2L, 1L, 200.0);
        accountRepository.save(dto1);
        accountRepository.save(dto2);
        List<AccountDTO> accounts = accountRepository.findByCustomerId(1L);
        assertEquals(2, accounts.size());
    }

    @Test
    void testFindAll() {
        AccountDTO dto1 = new AccountDTO(1L, 1L, 100.0);
        AccountDTO dto2 = new AccountDTO(2L, 2L, 200.0);
        accountRepository.save(dto1);
        accountRepository.save(dto2);
        List<AccountDTO> accounts = accountRepository.findAll();
        assertEquals(2, accounts.size());
    }

    @Test
    void testDeleteById() {
        AccountDTO dto = new AccountDTO(1L, 1L, 100.0);
        accountRepository.save(dto);
        accountRepository.deleteById(1L);
        AccountDTO account = accountRepository.findById(1L);
        assertNull(account);
    }
}
