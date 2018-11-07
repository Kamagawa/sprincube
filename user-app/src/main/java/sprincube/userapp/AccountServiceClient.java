package sprincube.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceClient {

    private final Logger logger = LoggerFactory.getLogger(URestController.class);

    private RestTemplate restTemplate;

    @Value("${ACCOUNT-SERVICE:account-service}")
    private String URL;

    @Value("${ACCOUNT-PORT:}")
    private String PORT;

    private String friendURL = ("http://"+ URL+ ":" +PORT);

    @Autowired
    public AccountServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "defaultUser")
    public Map getUserInfo() {
        String friendURL = ("http://"+ URL+ ":" +PORT);
        logger.info("Getting User at "+ friendURL);
        Map result = restTemplate.getForObject(friendURL, Map.class);
        logger.info("getUSer(): " + result.toString());
        return result;
    }
    @HystrixCommand(fallbackMethod = "defaultUser")
    public Map<String, String> getUserById(int i) {
        String accountIdUrl = ("http://"+ URL+ ":" +PORT) +"/"+ Integer.toString(i);
        logger.info("Getting User" + accountIdUrl);
        Map<String,String> result = restTemplate .getForObject(accountIdUrl, Map.class);
        return result;
    }


    public Map<String, String> defaultUser() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Empty", "User");
        return map;
    }

    public Map<String, String> defaultUser(int id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("EmptyUser", Integer.toString(id))  ;
        return map;
    }


}
