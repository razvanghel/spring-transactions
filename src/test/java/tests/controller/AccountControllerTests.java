package tests.controller;

import com.transactionsexample.spring_transactions.controller.AccountController;
import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private AccountResponseDTO accountResponseDTO;

    @BeforeEach
    void setUp() {
        accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(1L);
        accountResponseDTO.setCustomerId(1L);
        accountResponseDTO.setBalance(100.0);
    }

    @Test
    void createAccount() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setCustomerId(1L);
        accountRequestDTO.setBalance(100.0);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":1,\"balance\":100.0}")
                )
                .andExpect(status().isOk());

        verify(accountService, times(1)).createAccount(any(AccountRequestDTO.class));
    }

    @Test
    void getAccountById() throws Exception {
        when(accountService.getAccountById(1L)).thenReturn(accountResponseDTO);

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.balance").value(100.0));
    }

    @Test
    void getAccountsByCustomerId() throws Exception {
        List<AccountResponseDTO> accounts = Arrays.asList(accountResponseDTO);
        when(accountService.getAccountsByCustomerId(1L)).thenReturn(accounts);

        mockMvc.perform(get("/accounts/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customerId").value(1))
                .andExpect(jsonPath("$[0].balance").value(100.0));
    }

    @Test
    void getAllAccounts() throws Exception {
        List<AccountResponseDTO> accounts = Arrays.asList(accountResponseDTO);
        when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customerId").value(1))
                .andExpect(jsonPath("$[0].balance").value(100.0));
    }

    @Test
    void deleteAccountById() throws Exception {
        mockMvc.perform(delete("/accounts/1"))
                .andExpect(status().isOk());

        verify(accountService, times(1)).deleteAccountById(1L);
    }

    @Configuration
    @ComponentScan(basePackages = "com.transactionsexample.spring_transactions")
    static class TestConfig {
        // Additional test-specific beans can be defined here
    }
}
