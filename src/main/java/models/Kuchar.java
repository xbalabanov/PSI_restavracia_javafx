package models;

/**
 * Model class representing a cook (Kuchar) in the system.
 * Stores basic information such as ID, name, and current status.
 */

public class Kuchar {
    private int id;
    private String meno;
    private String stav; // "dostupny", "pripravauje", "odpociva"

    /**
     * Creates a cook with specified values.
     *
     * @param id cook ID
     * @param meno cook name
     * @param stav current status (e.g., dostupny, pripravuje, odpociva)
     */
    public Kuchar(int id, String meno, String stav) {
        this.id = id;
        this.meno = meno;
        this.stav = stav;
    }

    /**
     * Default constructor initializing cook with default values.
     */
    public Kuchar() {
        this(0, "", "dostupny");
    }

    /**
     * Getter and setter methods.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets cook ID.
     *
     * @param id cook ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns cook name.
     *
     * @return name
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Sets cook name.
     *
     * @param meno name
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     * Returns current cook status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets cook status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns string representation of the cook.
     *
     * @return string with cook details
     */
    @Override
    public String toString() {
        return "Kuchar{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", stav='" + stav + '\'' +
                '}';
    }
}
