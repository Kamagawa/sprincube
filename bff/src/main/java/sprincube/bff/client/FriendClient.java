package sprincube.bff.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.controller.ApiController;

import java.util.Map;

@Component
public class FriendClient {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private RestTemplate restTemplate;
    private Path path;

    @Value("${FRIEND-SERVICE:friend-service}")
    private String URL;

    @Value("${FRIEND-PORT:}")
    private String PORT;

    @Autowired
    public FriendClient(RestTemplate restTemplate, Path path) {
        this.restTemplate = restTemplate;
        this.path = path;
    }

    public Iterable getFriend() {
        String url = UriComponentsBuilder.fromHttpUrl(path.friendURL).path("/api/friend").toUriString();
        if (logger.isDebugEnabled()) logger.info(String.format("Getting friend from %s", url));
        Iterable it = restTemplate.getForObject(url, Iterable.class);
        if (logger.isDebugEnabled()) logger.info((it==null)? "":it.toString());
        return it;
    }

    public Map getFriendById(String id) {
        logger.info("Getting friend from FriendServiceClient");
        String url = UriComponentsBuilder.fromHttpUrl(path.friendURL).path("/api/friend").queryParam("id", id).toUriString();
        Map map = restTemplate.getForObject(url, Map.class);
        return map;
    }


}
