package sprincube.account.domain;

import org.junit.Test;

//reserved for elements of the Account domain not tested in the controllers and services' test
public class AccountTest {

    @Test
    public void accountAttributeTest() {
        Account account = new Account();
        account.setId(99L);
        account.setName("F");
        account.setDucats(99D);
        account.setNumber(99D);

        assert account.getDucats()==99D
                && account.getNumber() == 99D
                && account.getName().equals("F")
                && account.getId() == 99L;
    }
}
