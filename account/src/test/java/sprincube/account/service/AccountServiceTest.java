package sprincube.account.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import sprincube.account.domain.Account;

import java.util.List;

@RunWith(SpringRunner.class)
public class AccountServiceTest {
    private AccountService accountService;


    @Test
    public void findUsers(){
        List<Account> list = accountService.findAll();
        System.out.println(list);
        assert list.size()>0;
        long distinctSize =list.stream().distinct().count();
        assert distinctSize == list.size();
        list.stream().forEach( (account) -> {
            assert account != null;
            assert account.getId() !=null;
            assert account.getName() !=null;
            assert account.getDucats() !=null;
        });
    }

    @Test
    public void findUserById(){
        List<Account> list = accountService.findAll(0L);
        System.out.println(list);
        assert list.size() ==1;
        Account account = list.get(0);
        assert account !=null;
        assert account.getId() !=null;
        assert account.getName() !=null;
        assert account.getNumber() !=null;
        assert account.getDucats() !=null;
    }

    @Before
    public void setUp() {
        accountService = new AccountService();

    }
}
