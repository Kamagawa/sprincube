package sprincube.account.controller;

import org.springframework.web.bind.annotation.*;
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
    public List<Account> findUser(@RequestParam(required = false) Long Id) {
        if (Id==null) return accountService.findAll();
        return accountService.findAll(Id);
    }
}
