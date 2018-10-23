package sprincube.friendservice;

import org.junit.Before;
import org.junit.Test;

public class IntegerTest {
    Integer two;

    public IntegerTest(){
        two = new Integer(2);
    }

    @Test
    public void two_is_2(){
        assert (two == 2);
    }

    @Test
    public void two_plus_1_is_3(){
        two++;
        assert (two == 3);
    }



}
