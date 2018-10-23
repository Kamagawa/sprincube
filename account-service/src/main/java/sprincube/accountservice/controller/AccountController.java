package sprincube.accountservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {
    private static Logger log = LoggerFactory.getLogger(AccountController.class);


    @GetMapping("/")
    public Map<String, String> getUser() {
        log.info("retrieving user");
        Map<String, String> map = new HashMap<>();
        map.put("User v1", "New");
        return map;
    }

    @GetMapping("/{Id}")
    public Map<String, String> getUserById(@PathVariable String Id) {
        log.info("retrieving user with id");
        Map<String, String> map = new HashMap<>();
        map.put("User v1", Id);
        return map;
    }
}
