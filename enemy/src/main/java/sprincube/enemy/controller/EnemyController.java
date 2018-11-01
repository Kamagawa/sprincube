package sprincube.enemy.controller;

import conf.FakeEnemy;
import sprincube.enemy.domain.Enemy;
import sprincube.enemy.repo.EnemyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController()
public class EnemyController {
    private static Logger log = LoggerFactory.getLogger(EnemyController.class);

    private EnemyRepository friendRepo;


    public EnemyController(EnemyRepository friendRepo){
        this.friendRepo = friendRepo;
    }

    @RequestMapping("/")
    public Iterable<Enemy> getAll(){
        return friendRepo.findAll();
    }

    @RequestMapping("/{friendID}")
    public Optional<Enemy> getFriend(@PathVariable int friendID){
        return friendRepo.findById(friendID);
    }

    @RequestMapping("/makeEnemies")
    public void makeFriends(@RequestParam(required = false) Integer number){
        if (number == 0){ number++;    }
        ArrayList<Enemy> list = new ArrayList<>();
        for(int i = 0; i < number; i++){
            list.add(FakeEnemy.make());
        }
        friendRepo.saveAll(list);
    }
    /*

    @Autowired
    FriendService friendService;

    @GetMapping("/")
    public Map<String, Friend> getFriends() {
        log.info("retrieving friend");

        return friendService.getAll();
    }

    @GetMapping("/{friendID}")
    public Friend getFriend(@PathVariable String friendID) {
        log.info("retrieving friend type");
        return friendService.find(friendID);
    }
    */
}
