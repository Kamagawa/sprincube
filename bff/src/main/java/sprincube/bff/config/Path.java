package sprincube.bff.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "url")
public class Path {
    public String accountURL;
    public String friendURL;


    public String getAccountURL() {
        return accountURL;
    }

    public Path setAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    public String getFriendURL() {
        return friendURL;
    }

    public Path setFriendURL(String friendURL) {
        this.friendURL = friendURL;
        return this;
    }
}