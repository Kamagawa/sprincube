package sprincube.account.controller;

import org.springframework.web.bind.annotation.*;
import sprincube.account.domain.Account;
import sprincube.account.service.AccountService;

import java.util.List;

/**
 * The type Account controller.
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    /**
     * Instantiates a new Account controller.
     *
     * @param accountService the account service
     */
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    /**
     * Find user list.
     *
     * @param id the id
     * @return the list
     */
    @GetMapping()
    public List<Account> findUser(@RequestParam(required = false) Long id) {
        if (id==null) return accountService.findAll();
        return accountService.findAll(id);
    }
}
