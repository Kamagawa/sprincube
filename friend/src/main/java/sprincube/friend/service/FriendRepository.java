package sprincube.friend.service;
import sprincube.friend.domain.Friend;
import org.springframework.data.repository.CrudRepository;


/**
 * The interface Friend repository.
 */
public interface FriendRepository extends CrudRepository<Friend, Integer> {}
