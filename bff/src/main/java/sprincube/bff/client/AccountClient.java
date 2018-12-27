package sprincube.bff.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.domain.Account;

import java.util.Collections;
import java.util.List;

/**
 * The type Account client.
 */
@Component
public class AccountClient {
    private RestTemplate restTemplate;
    private Path path;

    /**
     * Instantiates a new Account client.
     *
     * @param restTemplate the rest template
     * @param path         the path
     */
    @Autowired
    public AccountClient(RestTemplate restTemplate, Path path) {
        this.restTemplate = restTemplate;
        this.path = path;
    }

    /**
     * Find account list.
     *
     * @return the list
     */
    @HystrixCommand(fallbackMethod = "defaultFindAccount")
    public List<Account> findAccount() {
        String url = UriComponentsBuilder.fromHttpUrl(path.getAccountURL()).path("/api/account").toUriString();
        ResponseEntity<List<Account>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Account>>(){});
        return response.getBody();
    }

    /**
     * Find account list.
     *
     * @param i the
     * @return the list
     */
    @HystrixCommand(fallbackMethod = "defaultFindAccount")
    public List<Account> findAccount(int i) {
        String url = UriComponentsBuilder.fromHttpUrl(path.getAccountURL()).path("/api/account").queryParam("Id", i).toUriString();
        ResponseEntity<List<Account>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Account>>(){});
        return response.getBody();
    }

    /**
     * Default find account list.
     *
     * @return the list
     */
    public List<Account> defaultFindAccount() {
        return Collections.singletonList(new Account(0L, "empty", 0D, 0D));
    }

    /**
     * Default find account list.
     *
     * @param id the id
     * @return the list
     */
    public List<Account> defaultFindAccount(int id) {
        return Collections.singletonList(new Account((long)id, "empty", 0D, 0D));
    }
}
