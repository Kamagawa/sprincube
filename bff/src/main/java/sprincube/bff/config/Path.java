package sprincube.bff.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Path.
 */
@Configuration
@ConfigurationProperties(prefix = "url")
public class Path {
    /**
     * The Account url.
     */
    private String accountURL;
    /**
     * The Friend url.
     */
    private String friendURL;


    /**
     * Gets account url.
     *
     * @return the account url
     */
    public String getAccountURL() {
        return accountURL;
    }

    /**
     * Sets account url.
     *
     * @param accountURL the account url
     * @return the account url
     */
    public Path setAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    /**
     * Gets friend url.
     *
     * @return the friend url
     */
    public String getFriendURL() {
        return friendURL;
    }

    /**
     * Sets friend url.
     *
     * @param friendURL the friend url
     * @return the friend url
     */
    public Path setFriendURL(String friendURL) {
        this.friendURL = friendURL;
        return this;
    }
}