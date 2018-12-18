package sprincube.bff.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.controller.ApiController;
import sprincube.bff.domain.Account;
import sprincube.bff.domain.Friend;

import java.util.List;
import java.util.Map;

@Component
public class FriendClient {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private RestTemplate restTemplate;
    private Path path;

    @Autowired
    public FriendClient(RestTemplate restTemplate, Path path) {
        this.restTemplate = restTemplate;
        this.path = path;
    }

    public Iterable<Friend> findFriend() {
        String url = UriComponentsBuilder.fromHttpUrl(path.friendURL).path("/api/friend").toUriString();
        ResponseEntity<Iterable<Friend>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Friend>>(){});
        return response.getBody();
    }

    public Iterable<Friend> findFriend(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(path.friendURL).path("/api/friend").queryParam("id", id).toUriString();
        ResponseEntity<Iterable<Friend>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Friend>>(){});
        return response.getBody();
    }


}
