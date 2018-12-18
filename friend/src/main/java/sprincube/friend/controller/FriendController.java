package sprincube.friend.controller;

import sprincube.friend.conf.FriendGenerator;
import sprincube.friend.domain.Friend;
import sprincube.friend.service.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController()
@RequestMapping("/api/friend")
public class FriendController {
    private static Logger log = LoggerFactory.getLogger(FriendController.class);
    private FriendRepository friendRepo;
    public FriendController(FriendRepository friendRepo){
        this.friendRepo = friendRepo;
    }

    @RequestMapping()
    public Iterable<Friend> find(@RequestParam(required = false) Integer Id) {
        if (Id !=null) return friendRepo.findAllById(Collections.singleton(Id));
        return friendRepo.findAll();
    }


    @RequestMapping("/generate")
    public void makeFriends(@RequestParam(required = false) Integer number){
        if (number == 0){ number++;    }
        ArrayList<Friend> list = new ArrayList<>();
        for(int i = 0; i < number; i++){
            list.add(FriendGenerator.make());
        }
        friendRepo.saveAll(list);
    }
}
