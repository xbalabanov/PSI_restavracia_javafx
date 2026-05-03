package models;

/**
 * Model class representing a waiter (Casnik) in the system.
 * Stores basic information such as ID, name, and login status.
 */
public class Casnik {
    /**
     * Creates a waiter with specified values.
     *
     * @param id waiter ID
     * @param meno waiter name
     * @param prihlaseny login status
     */
    public Casnik(int id, String meno, boolean prihlaseny) {
        this.id = id;
        this.meno = meno;
        this.prihlaseny = prihlaseny;
    }

    /**
     * Default constructor initializing empty waiter.
     */
    public Casnik() {
        this(0, "", false);
    }

    /**
     * Getter and setter methods.
     */
    private int id;
    private String meno;
    private boolean prihlaseny;

    /**
     * Returns waiter ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets waiter ID.
     *
     * @param id waiter ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns waiter name.
     *
     * @return name
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Sets waiter name.
     *
     * @param meno name
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     * Returns login status.
     *
     * @return true if logged in
     */
    public boolean isPrihlaseny() {
        return prihlaseny;
    }

    /**
     * Sets login status.
     *
     * @param prihlaseny login state
     */
    public void setPrihlaseny(boolean prihlaseny) {
        this.prihlaseny = prihlaseny;
    }

    /**
     * Returns string representation of the waiter.
     *
     * @return string with waiter details
     */
    @Override
    public String toString() {
        return "Casnik{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", prihlaseny=" + prihlaseny +
                '}';
    }
}
