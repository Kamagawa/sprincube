package sprincube.bff.domain;

public class Friend extends Base {
    public static final int ACQUAINTANCE = 0;
    public static final int CASUAL_FRIEND = 1;
    public static final int CLOSE_FRIEND = 2;
    public static final int PARTNER = 3;

    private int id;
    private String name;
    private int type;

    public Friend() { }

    public Friend(String name, int type) {
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
