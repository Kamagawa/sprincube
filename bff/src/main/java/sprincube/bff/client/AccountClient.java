package sprincube.bff.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.util.UriComponentsBuilder;
import sprincube.bff.config.Path;
import sprincube.bff.controller.ApiController;
import sprincube.bff.domain.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @HystrixCommand(fallbackMethod = "defaultUser")
    public List<Account> getUserInfo() {
        String url = UriComponentsBuilder.fromHttpUrl(path.accountURL).path("/api/account").toUriString();
        logger.info(String.format("url %s", url));
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>(){});
        List<Account> accounts = response.getBody();
        Map result = restTemplate.getForObject(url, Map.class);
        if (logger.isDebugEnabled()) {
            logger.info(String.format("url %s", url));
            logger.info(String.format("Getting User at %s", url));
            logger.info(String.format("getUSer(): %s", result.toString()));
        }
        return accounts;
    }

    @HystrixCommand(fallbackMethod = "defaultUser")
    public Map<String, String> getUserById(int i) {
        String url = UriComponentsBuilder.fromHttpUrl(path.accountURL).path("/api/account").queryParam("Id", i).toUriString();
        if (logger.isDebugEnabled()) {
            logger.info(String.format("Getting User%s", url));
        }
        Map<String, String> result = restTemplate.getForObject(url, Map.class);
        return result;
    }


    public Map<String, String> defaultUser() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Empty", "User");
        return map;
    }

    public Map<String, String> defaultUser(int id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("EmptyUser", Integer.toString(id));
        return map;
    }


}
