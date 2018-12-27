package sprincube.friend.conf;

import sprincube.friend.domain.Friend;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Friend generator.
 */
public class FriendGenerator {
    /**
     * The One.
     */
    static int one = 0;

    /**
     * Make friend.
     *
     * @return the friend
     */
    private FriendGenerator(){
        throw new IllegalStateException("Utility Class");
    }

    public static Friend make(){
        int number = ThreadLocalRandom.current().nextInt(0,5);
        one++;
        return new Friend("FRIEND" + one, number);
    }
}
