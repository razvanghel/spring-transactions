package tests.service;

import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.repository.TransactionRepository;
import com.transactionsexample.spring_transactions.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAmount(100.0);
        transactionDTO.setTimestamp(LocalDateTime.now());

        transactionService.createTransaction(transactionDTO);
        verify(transactionRepository, times(1)).save(transactionDTO);
    }

    @Test
    void getTransactionById() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAmount(100.0);
        transactionDTO.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findById(1L)).thenReturn(transactionDTO);

        TransactionDTO foundTransaction = transactionService.getTransactionById(1L);
        assertNotNull(foundTransaction);
        assertEquals(100.0, foundTransaction.getAmount());
    }

    @Test
    void getAllTransactions() {
        TransactionDTO transaction1 = new TransactionDTO();
        transaction1.setId(1L);
        transaction1.setAmount(100.0);
        transaction1.setTimestamp(LocalDateTime.now());

        TransactionDTO transaction2 = new TransactionDTO();
        transaction2.setId(2L);
        transaction2.setAmount(200.0);
        transaction2.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        assertEquals(2, transactions.size());
    }

    @Test
    void deleteTransactionById() {
        // Mock the behavior to return true when checking if the transaction exists
        when(transactionRepository.existsById(1L)).thenReturn(true);

        // Call the method under test
        transactionService.deleteTransactionById(1L);

        // Verify that deleteById was called
        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    void getTransactionsByAccountId() {
        TransactionDTO transaction1 = new TransactionDTO();
        transaction1.setId(1L);
        transaction1.setAccountId(1L);
        transaction1.setAmount(100.0);
        transaction1.setTimestamp(LocalDateTime.now());

        TransactionDTO transaction2 = new TransactionDTO();
        transaction2.setId(2L);
        transaction2.setAccountId(1L);
        transaction2.setAmount(200.0);
        transaction2.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findByAccountId(1L)).thenReturn(Arrays.asList(transaction1, transaction2));

        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(1L);
        assertEquals(2, transactions.size());
    }
}
