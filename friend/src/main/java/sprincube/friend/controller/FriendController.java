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
import java.util.Optional;

@RestController()
public class FriendController {
    private static Logger log = LoggerFactory.getLogger(FriendController.class);

    private FriendRepository friendRepo;


    public FriendController(FriendRepository friendRepo){
        this.friendRepo = friendRepo;
    }

    @RequestMapping()
    public Iterable<Friend> getAll(){
        return friendRepo.findAll();
    }

    @RequestMapping("/{friendID}")
    public Optional<Friend> getFriend(@PathVariable int friendID){
        return friendRepo.findById(friendID);
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
