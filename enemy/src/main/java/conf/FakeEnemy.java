package conf;

import sprincube.enemy.domain.Enemy;

import java.util.concurrent.ThreadLocalRandom;

public class FakeEnemy {
    static int one = 0;
    public static Enemy make(){
        int number = ThreadLocalRandom.current().nextInt(0,5);
        one++;
        return new Enemy(
                "FRIEND" + one, number
        );

    }
}
