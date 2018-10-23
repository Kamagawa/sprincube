
package sprincube.friendservice.conf;


import sprincube.friendservice.domain.Friend;
import sprincube.friendservice.service.FriendRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FriendConfiguration {

    @Bean
    public CommandLineRunner runner(FriendRepository friendService) {
        return args -> {
            @SuppressWarnings("serial")
            List<Friend> friends = new ArrayList<Friend>() {
                {
                    add(new Friend("Yoshi Hito", Friend.ACQUAINTANCE));
                    add(new Friend("KURO FURENDO", Friend.CLOSE_FRIEND));
                    add(new Friend("KASU FURENDO", Friend.CASUAL_FRIEND));
                    add(new Friend("PATO SHIBA", Friend.PARTNER));
                }
            };
            friendService.saveAll(friends);

        };
    }
}
