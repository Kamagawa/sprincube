package sprincube.friend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sprincube.friend.conf.FriendGenerator;
import sprincube.friend.domain.Friend;
import sprincube.friend.service.FriendRepository;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Friend controller.
 */
@RestController
@RequestMapping("/api/friend")
public class FriendController {
    private FriendRepository friendRepository;

    /**
     * Instantiates a new Friend controller.
     *
     * @param friendRepository the friend repository
     */
    public FriendController(FriendRepository friendRepository){
        this.friendRepository = friendRepository;
    }

    /**
     * Find iterable.
     *
     * @param id the id
     * @return the iterable
     */
    @RequestMapping()
    public Iterable<Friend> find(@RequestParam(required = false) Integer id) {
        if (id !=null) return friendRepository.findAllById(Collections.singleton(id));
        return friendRepository.findAll();
    }


    /**
     * Make friends.
     *
     * @param number the number
     */
    @RequestMapping("/generate")
    public void makeFriends(@RequestParam(required = false) Integer number){
        if (number == 0){ number++;    }
        ArrayList<Friend> list = new ArrayList<>();
        for(int i = 0; i < number; i++){
            list.add(FriendGenerator.make());
        }
        friendRepository.saveAll(list);
    }
}
