package sprincube.userapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class URestController {

    private final Logger logger = LoggerFactory.getLogger(URestController.class);

    private AccountServiceClient accountServiceClient;
    private FriendServiceClient friendServiceClient;


    public URestController(AccountServiceClient accountServiceClient, FriendServiceClient friendServiceClient) {
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
