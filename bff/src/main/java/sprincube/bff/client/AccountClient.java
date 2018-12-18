package sprincube.bff.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.controller.ApiController;
import sprincube.bff.domain.Account;

import java.util.Collections;
import java.util.List;

@Component
public class AccountClient {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private RestTemplate restTemplate;
    private Path path;

    @Autowired
    public AccountClient(RestTemplate restTemplate, Path path) {
        this.restTemplate = restTemplate;
        this.path = path;
    }

    @HystrixCommand(fallbackMethod = "defaultFindAccount")
    public List<Account> findAccount() {
        String url = UriComponentsBuilder.fromHttpUrl(path.accountURL).path("/api/account").toUriString();
        logger.info(String.format("url %s", url));
        ResponseEntity<List<Account>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Account>>(){});
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "defaultFindAccount")
    public List<Account> findAccount(int i) {
        String url = UriComponentsBuilder.fromHttpUrl(path.accountURL).path("/api/account").queryParam("Id", i).toUriString();
        ResponseEntity<List<Account>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Account>>(){});
        return response.getBody();
    }

    public List<Account> defaultFindAccount() {
        return Collections.singletonList(new Account(0L, "empty", 0D, 0D));
    }

    public List<Account> defaultFindAccount(int id) {
        return Collections.singletonList(new Account((long)id, "empty", 0D, 0D));
    }
}
