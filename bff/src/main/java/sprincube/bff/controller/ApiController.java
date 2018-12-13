package sprincube.bff.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sprincube.bff.client.AccountServiceClient;
import sprincube.bff.client.FriendServiceClient;

import java.util.Map;

@RestController("/api")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private AccountServiceClient accountServiceClient;
    private FriendServiceClient friendServiceClient;


    public ApiController(AccountServiceClient accountServiceClient, FriendServiceClient friendServiceClient) {
        this.accountServiceClient = accountServiceClient;
        this.friendServiceClient = friendServiceClient;
    }

    @GetMapping("/user")
    Map<String, String> user() {
        logger.info("Getting Default User");
        return accountServiceClient.getUserInfo();
    }

    @GetMapping("/user/{id}")
    Map<String, String> getUser(@PathVariable int id) {

        logger.info("Getting User");
        return accountServiceClient.getUserById(id);
    }

    @GetMapping("/user/info")
    Map<String, String> getUserInfo() {

        logger.info("Adding user info");
        return accountServiceClient.getUserInfo();
    }

    @GetMapping("/friend")
    Iterable getFriend() {
        logger.info("Getting friend");
        return friendServiceClient.getFriend();
    }

    @GetMapping("/friend/{id}")
    Map getFriendType(@PathVariable String id) {

        logger.info("Getting friend");
        return friendServiceClient.getFriendById(id);
    }


}
