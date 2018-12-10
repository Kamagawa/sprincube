package sprincube.accountservice.service;

import org.springframework.stereotype.Service;
import sprincube.accountservice.domain.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Account service.
 */

@Service
public class AccountService {

    /**
     * The List that stores the accounts objects.
     */
    List<Account> list;

    /**
     * Instantiates a new Account service with test account stored.
     */
    public AccountService() {
        list = new ArrayList<>();
        testAccount(list);
    }


    private void testAccount(List list) {
        for (int i = 0; i < 10; i ++){
            list.add(new Account((long)i, String.format("ROBOT[%s]", i),(double)20181210*1000+i, Math.random()*1-000-00));
        }
    }

    public List<Account> findAll(){
        return list;
    }

    public List<Account> findAll(Long id){
        return list.stream().filter((account -> account.getId().equals(id))).collect(Collectors.toList());
    }
}
