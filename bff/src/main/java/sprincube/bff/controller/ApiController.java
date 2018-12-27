package sprincube.bff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sprincube.bff.client.AccountClient;
import sprincube.bff.client.FriendClient;
import sprincube.bff.domain.Account;
import sprincube.bff.domain.Friend;

import java.util.List;

/**
 * The type Api controller.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private AccountClient accountClient;
    private FriendClient friendClient;

    /**
     * Instantiates a new Api controller.
     *
     * @param accountClient the account client
     * @param friendClient  the friend client
     */
    public ApiController(AccountClient accountClient, FriendClient friendClient) {
        this.accountClient = accountClient;
        this.friendClient = friendClient;
    }

    /**
     * Find accounts list.
     *
     * @return the list
     */
    @GetMapping("/account")
    List<Account> findAccounts() {
        return accountClient.findAccount();
    }

    /**
     * Find account list.
     *
     * @param id the id
     * @return the list
     */
    @GetMapping("/account/{id}")
    List<Account> findAccount(@PathVariable int id) {
        return accountClient.findAccount(id);
    }

    /**
     * Find friend iterable.
     *
     * @return the iterable
     */
    @GetMapping("/friend")
    Iterable<Friend> findFriend() {
        return friendClient.findFriend();
    }

    /**
     * Find friend iterable.
     *
     * @param id the id
     * @return the iterable
     */
    @GetMapping("/friend/{id}")
    Iterable<Friend> findFriend(@PathVariable String id) {
        return friendClient.findFriend(id);
    }
}
