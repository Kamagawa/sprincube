package sprincube.friend.conf;

import sprincube.friend.domain.Friend;

import java.util.concurrent.ThreadLocalRandom;

public class FriendGenerator {
    static int one = 0;
    public static Friend make(){
        int number = ThreadLocalRandom.current().nextInt(0,5);
        one++;
        return new Friend("FRIEND" + one, number);
    }
}
