package sprincube.bff.domain;

/**
 * The type Friend.
 */
public class Friend extends Base {
    /**
     * The constant ACQUAINTANCE.
     */
    public static final int ACQUAINTANCE = 0;
    /**
     * The constant CASUAL_FRIEND.
     */
    public static final int CASUAL_FRIEND = 1;
    /**
     * The constant CLOSE_FRIEND.
     */
    public static final int CLOSE_FRIEND = 2;
    /**
     * The constant PARTNER.
     */
    public static final int PARTNER = 3;

    private int id;
    private String name;
    private int type;

    /**
     * Instantiates a new Friend.
     */
    public Friend() { }

    /**
     * Instantiates a new Friend.
     *
     * @param name the name
     * @param type the type
     */
    public Friend(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }
}
