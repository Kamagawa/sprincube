package sprincube.friendservice.service;
import sprincube.friendservice.domain.Friend;
import org.springframework.data.repository.CrudRepository;


public interface FriendRepository extends CrudRepository<Friend, Integer> {}
