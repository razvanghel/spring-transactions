package tests.repository_impl;

import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.repository_impl.TransactionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TransactionRepositoryImplTests {

    private TransactionRepositoryImpl transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositoryImpl();
    }

    @Test
    void testSave() {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setAccountId(1L);
        dto.setAmount(100.0);
        dto.setTimestamp(LocalDateTime.now());

        transactionRepository.save(dto);
        TransactionDTO transaction = transactionRepository.findById(1L);
        assertNotNull(transaction);
        assertEquals(1L, transaction.getAccountId());
        assertEquals(100.0, transaction.getAmount());
    }

    @Test
    void testFindById() {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setAccountId(1L);
        dto.setAmount(100.0);
        dto.setTimestamp(LocalDateTime.now());

        transactionRepository.save(dto);
        TransactionDTO transaction = transactionRepository.findById(1L);
        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
    }

    @Test
    void testFindAll() {
        TransactionDTO dto1 = new TransactionDTO();
        dto1.setId(1L);
        dto1.setAccountId(1L);
        dto1.setAmount(100.0);
        dto1.setTimestamp(LocalDateTime.now());

        TransactionDTO dto2 = new TransactionDTO();
        dto2.setId(2L);
        dto2.setAccountId(2L);
        dto2.setAmount(200.0);
        dto2.setTimestamp(LocalDateTime.now());

        transactionRepository.save(dto1);
        transactionRepository.save(dto2);

        List<TransactionDTO> transactions = transactionRepository.findAll();
        assertEquals(2, transactions.size());
    }

    @Test
    void testDeleteById() {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setAccountId(1L);
        dto.setAmount(100.0);
        dto.setTimestamp(LocalDateTime.now());

        transactionRepository.save(dto);
        transactionRepository.deleteById(1L);
        TransactionDTO transaction = transactionRepository.findById(1L);
        assertNull(transaction);
    }

    @Test
    void testFindByAccountId() {
        TransactionDTO dto1 = new TransactionDTO();
        dto1.setId(1L);
        dto1.setAccountId(1L);
        dto1.setAmount(100.0);
        dto1.setTimestamp(LocalDateTime.now());

        TransactionDTO dto2 = new TransactionDTO();
        dto2.setId(2L);
        dto2.setAccountId(1L);
        dto2.setAmount(200.0);
        dto2.setTimestamp(LocalDateTime.now());

        transactionRepository.save(dto1);
        transactionRepository.save(dto2);

        List<TransactionDTO> transactions = transactionRepository.findByAccountId(1L);
        assertEquals(2, transactions.size());
    }
}