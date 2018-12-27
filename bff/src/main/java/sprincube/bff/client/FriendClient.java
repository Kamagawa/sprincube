package sprincube.bff.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.domain.Friend;

/**
 * The type Friend client.
 */
@Component
public class FriendClient {
    private RestTemplate restTemplate;
    private Path path;

    /**
     * Instantiates a new Friend client.
     *
     * @param restTemplate the rest template
     * @param path         the path
     */
    @Autowired
    public FriendClient(RestTemplate restTemplate, Path path) {
        this.restTemplate = restTemplate;
        this.path = path;
    }

    /**
     * Find friend iterable.
     *
     * @return the iterable
     */
    public Iterable<Friend> findFriend() {
        String url = UriComponentsBuilder.fromHttpUrl(path.getFriendURL()).path("/api/friend").toUriString();
        ResponseEntity<Iterable<Friend>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Friend>>(){});
        return response.getBody();
    }

    /**
     * Find friend iterable.
     *
     * @param id the id
     * @return the iterable
     */
    public Iterable<Friend> findFriend(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(path.getFriendURL()).path("/api/friend").queryParam("id", id).toUriString();
        ResponseEntity<Iterable<Friend>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Friend>>(){});
        return response.getBody();
    }


}
