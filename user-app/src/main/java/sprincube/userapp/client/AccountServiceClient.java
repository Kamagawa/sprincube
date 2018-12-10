package sprincube.userapp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import sprincube.userapp.controller.ApiController;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceClient {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private RestTemplate restTemplate;

    @Value("${ACCOUNT-SERVICE:account-service}")
    private String URL;

    @Value("${ACCOUNT-PORT:}")
    private String PORT;

    @Autowired
    public AccountServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "defaultUser")
    public Map getUserInfo() {
        String friendURL = ("http://"+ URL+ ":" +PORT);
        Map result = restTemplate.getForObject(friendURL, Map.class);
        if (logger.isDebugEnabled()){
            logger.info(String.format("Getting User at %s", friendURL));
            logger.info(String.format("getUSer(): %s", result.toString()));
        }
        return result;
    }
    @HystrixCommand(fallbackMethod = "defaultUser")
    public Map<String, String> getUserById(int i) {
        String accountIdUrl = ("http://"+ URL+ ":" +PORT) +"/"+ Integer.toString(i);
        if (logger.isDebugEnabled()){
            logger.info(String.format("Getting User%s", accountIdUrl));
        }
        Map<String,String> result = restTemplate.getForObject(accountIdUrl, Map.class);
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
