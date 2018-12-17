package sprincube.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sprincube.account.domain.Account;
import sprincube.account.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping()
    public List<Account> getUser() {
        return accountService.findAll();
    }

    @GetMapping("/{Id}")
    public List<Account> getUserById(@PathVariable Long Id) {
        return accountService.findAll(Id);
    }
}
