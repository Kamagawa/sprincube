package sprincube.friend.service;
import sprincube.friend.domain.Friend;
import org.springframework.data.repository.CrudRepository;


public interface FriendRepository extends CrudRepository<Friend, Integer> {}
