package sprincube.bff.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sprincube.bff.controller.ApiController;

import java.util.Map;

@Component
public class FriendServiceClient {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private RestTemplate restTemplate;

    @Value("${FRIEND-SERVICE:friend-service}")
    private String URL;

    @Value("${FRIEND-PORT:}")
    private String PORT;

    @Autowired
    public FriendServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //@HystrixCommand(fallbackMethod = "defaultFriend")
    public Iterable getFriend() {
        String friendUrl = "http://" + URL + ":" + PORT;
        logger.info("Getting friend from FriendServiceClient");
        Iterable it = restTemplate.getForObject(friendUrl, Iterable.class);
        return it;
    }

    //@HystrixCommand(fallbackMethod = "defaultType")
    public Map getFriendById(String id) {
        logger.info("Getting friend from FriendServiceClient");
        String friendTypeUrl = "http://" + URL + ":" + PORT + "/" + id;
        Map map = restTemplate.getForObject(friendTypeUrl, Map.class);
        return map;
    }



}
