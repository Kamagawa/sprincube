package sprincube.account.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sprincube.account.domain.Account;
import sprincube.account.service.AccountService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@EnableSpringDataWebSupport
public class AccountControllerTest {
    @MockBean private AccountService accountService;
    @Autowired private MockMvc mockMvc;
    private AccountController accountController;

    List<Account> list;

    @Test
    public void getUserTest() throws Exception {
        Mockito.when(accountService.findAll()).thenReturn(list);
        mockMvc.perform(get("/api/account"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
                // todo continue here

        Mockito.verify(accountService, Mockito.times(1)).findAll();
    }

    @Test
    public void findUserByIdTest() throws Exception {
        Mockito.when(accountService.findAll(2L)).thenReturn(list);
        mockMvc.perform(get("/api/account/2"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        // todo continue here

        Mockito.verify(accountService, Mockito.times(1)).findAll(2L);
    }

    @Before
    public void Setup() {
        accountController = new AccountController(accountService);
        generateAccounts(0xfff);
    }

    public void generateAccounts(int number) {
        if (list== null) list = new ArrayList<>();
        for (int i = 0; i < number; i ++) {
            list.add(new Account((long)i, "ROBOT_"+i, (double)i, (double)Math.random()*1000000000));
        }
    }
}
