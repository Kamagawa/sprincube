package sprincube.account.service;

import org.springframework.stereotype.Service;
import sprincube.account.domain.Account;

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
        testAccount();
    }


    private void testAccount() {
        if (list == null) list = new ArrayList<>();
        for (int i = 0; i < 10; i ++){
            list.add(new Account((long)i, String.format("ROBOT%s", i),(double)20181210*1000+i, Math.random()*100000));
        }
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Account> findAll(){
        return list;
    }

    /**
     * Find all list.
     *
     * @param id the id
     * @return the list
     */
    public List<Account> findAll(Long id){
        return list.stream().filter((account -> account.getId().equals(id))).collect(Collectors.toList());
    }
}
