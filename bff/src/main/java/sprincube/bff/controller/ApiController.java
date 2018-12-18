package sprincube.bff.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sprincube.bff.client.AccountClient;
import sprincube.bff.client.FriendClient;
import sprincube.bff.domain.Account;

import java.util.List;
import java.util.Map;

@RestController("/api")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private AccountClient accountClient;
    private FriendClient friendClient;


    public ApiController(AccountClient accountClient, FriendClient friendClient) {
        this.accountClient = accountClient;
        this.friendClient = friendClient;
    }

    @GetMapping("/user")
    List<Account> user() {
        logger.info("Getting Default User");
        return accountClient.getUserInfo();
    }

    @GetMapping("/user/{id}")
    Map<String, String> getUser(@PathVariable int id) {

        logger.info("Getting User");
        return accountClient.getUserById(id);
    }

    @GetMapping("/user/info")
    List<Account> getUserInfo() {

        logger.info("Adding user info");
        return accountClient.getUserInfo();
    }

    @GetMapping("/friend")
    Iterable getFriend() {
        logger.info("Getting friend");
        return friendClient.getFriend();
    }

    @GetMapping("/friend/{id}")
    Map getFriendType(@PathVariable String id) {

        logger.info("Getting friend");
        return friendClient.getFriendById(id);
    }


}
