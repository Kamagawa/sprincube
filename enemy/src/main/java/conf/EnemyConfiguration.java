
package conf;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sprincube.enemy.domain.Enemy;
import sprincube.enemy.repo.EnemyRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EnemyConfiguration {

    @Bean
    public CommandLineRunner runner(EnemyRepository enemyRepository) {
        return args -> {
            @SuppressWarnings("serial")
            List<Enemy> friends = new ArrayList<Enemy>() {
                {
                    add(new Enemy("Super Man", Enemy.RIVAL));
                    add(new Enemy("Bat Man", Enemy.RIVAL));
                    add(new Enemy("Iron Man", Enemy.BIGG_RIVAL));
                    add(new Enemy("ANt Man", Enemy.ENEMY));
                    add(new Enemy("MAN", Enemy.ARCH_ENEMY));
                }
            };
            enemyRepository.saveAll(friends);

        };
    }
}
