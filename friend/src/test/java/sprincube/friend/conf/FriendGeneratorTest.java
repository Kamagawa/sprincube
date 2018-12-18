package sprincube.friend.conf;

import org.junit.Before;
import org.junit.Test;
import sprincube.friend.domain.Friend;

public class FriendGeneratorTest {

    FriendGenerator friendGenerator;
    @Test
    public void FriendGenerate() {
        Friend friend = friendGenerator.make();
        //System.out.println(friend.getId());
        assert friend.getId()>=0;
        assert friend.getName()!=null;
        assert 0<=friend.getType() && friend.getType()<= 5;


    }

    @Test
    public void createFriend() {
        Friend friend = new Friend();
        friend.setId(55);
        friend.setName("FAKE");
        friend.setType(5);
        assert friend.getType() ==5 && friend.getId() == 55 && friend.getName().equals("FAKE");
    }

    @Before
    public void setup() {
        friendGenerator = new FriendGenerator();
    }


}
