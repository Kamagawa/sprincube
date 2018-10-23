package sprincube.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FriendServiceClient {

    private final Logger logger = LoggerFactory.getLogger(URestController.class);

    private RestTemplate restTemplate;

    @Value("${friendService.baseUrl}")
    private String friendUrl;

    @Autowired
    public FriendServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //@HystrixCommand(fallbackMethod = "defaultFriend")
    public Iterable getFriend() {
        logger.info("Getting friend from FriendServiceClient");
        Iterable it = restTemplate.getForObject(friendUrl, Iterable.class);
        return it;
    }

    //@HystrixCommand(fallbackMethod = "defaultType")
    public Map getFriendById(String id) {
        logger.info("Getting friend from FriendServiceClient");
        String friendTypeUrl = friendUrl + "/" + id;
        Map map = restTemplate.getForObject(friendTypeUrl, Map.class);
        return map;
    }



}
