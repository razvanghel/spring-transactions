package tests.repository_impl;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
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
        AccountRequestDTO dto = new AccountRequestDTO(1L, 100.0);
        accountRepository.save(dto);
        AccountResponseDTO account = accountRepository.findById(1L);
        assertNotNull(account);
        assertEquals(1L, account.getCustomerId());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testFindById() {
        AccountRequestDTO dto = new AccountRequestDTO(1L, 100.0);
        accountRepository.save(dto);
        AccountResponseDTO account = accountRepository.findById(1L);
        assertNotNull(account);
        assertEquals(1L, account.getId());
    }

    @Test
    void testFindByCustomerId() {
        AccountRequestDTO dto1 = new AccountRequestDTO(1L, 100.0);
        AccountRequestDTO dto2 = new AccountRequestDTO(2L, 200.0);
        accountRepository.save(dto1);
        accountRepository.save(dto2);
        List<AccountResponseDTO> accounts = accountRepository.findByCustomerId(1L);
        assertEquals(1, accounts.size());
    }

    @Test
    void testFindAll() {
        AccountRequestDTO dto1 = new AccountRequestDTO(1L, 100.0);
        AccountRequestDTO dto2 = new AccountRequestDTO(2L, 200.0);
        accountRepository.save(dto1);
        accountRepository.save(dto2);
        List<AccountResponseDTO> accounts = accountRepository.findAll();
        assertEquals(2, accounts.size());
    }

    @Test
    void testDeleteById() {
        AccountRequestDTO dto = new AccountRequestDTO(1L, 100.0);
        accountRepository.save(dto);
        accountRepository.deleteById(1L);
        AccountResponseDTO account = accountRepository.findById(1L);
        assertNull(account);
    }
}
