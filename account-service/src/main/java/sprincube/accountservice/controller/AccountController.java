package sprincube.accountservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sprincube.accountservice.domain.Account;
import sprincube.accountservice.service.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<Account> getUser() {
        return accountService.findAll();
    }

    @GetMapping("/{Id}")
    public List<Account> getUserById(@PathVariable Long Id) {
        return accountService.findAll(Id);
    }
}
