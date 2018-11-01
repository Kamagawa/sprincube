package sprincube.enemy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enemy {
    public static final int SMALL_RIVAL = 0;
    public static final int RIVAL = 1;
    public static final int BIGG_RIVAL = 2;
    public static final int ENEMY = 3;
    public static final int ARCH_ENEMY = 4;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private int type;




    public Enemy() { }

    public Enemy(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
