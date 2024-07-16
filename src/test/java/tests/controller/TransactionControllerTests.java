package tests.controller;

import com.transactionsexample.spring_transactions.controller.TransactionController;
import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAccountId(1L);
        transactionDTO.setAmount(100.0);
        transactionDTO.setTimestamp(LocalDateTime.now());
    }

    @Test
    void createTransaction() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountId\":1,\"amount\":100.0,\"timestamp\":\"2023-07-01T12:00:00\"}")
                )
                .andExpect(status().isCreated());

        verify(transactionRepository, times(1)).save(any(TransactionDTO.class));
    }

    @Test
    void getTransactionById() throws Exception {
        when(transactionRepository.findById(1L)).thenReturn(transactionDTO);

        mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void getAllTransactions() throws Exception {
        List<TransactionDTO> transactions = Arrays.asList(transactionDTO);
        when(transactionRepository.findAll()).thenReturn(transactions);

        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].accountId").value(1))
                .andExpect(jsonPath("$[0].amount").value(100.0));
    }

    @Test
    void deleteTransactionById() throws Exception {
        // Mocking the repository to return false when existsById is called
        when(transactionRepository.existsById(1L)).thenReturn(false);

        // Performing the delete request and expecting a 404 status
        mockMvc.perform(delete("/transactions/1"))
                .andExpect(status().isNotFound());

        // Verifying that deleteById is never called since the transaction does not exist
        verify(transactionRepository, never()).deleteById(1L);
    }

    @Test
    void getTransactionsByAccountId() throws Exception {
        List<TransactionDTO> transactions = Arrays.asList(transactionDTO);
        when(transactionRepository.findByAccountId(1L)).thenReturn(transactions);

        mockMvc.perform(get("/transactions/account/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].accountId").value(1))
                .andExpect(jsonPath("$[0].amount").value(100.0));
    }

    @Configuration
    @ComponentScan(basePackages = "com.transactionsexample.spring_transactions")
    static class TestConfig {
        // Additional test-specific beans can be defined here
    }
}
