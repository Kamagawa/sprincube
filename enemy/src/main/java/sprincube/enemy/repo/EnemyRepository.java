package sprincube.enemy.repo;

import org.springframework.data.repository.CrudRepository;
import sprincube.enemy.domain.Enemy;

public interface EnemyRepository extends CrudRepository<Enemy, Integer> {}
