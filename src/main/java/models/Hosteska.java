package models;

/**
 * Model class representing a hostess in the system.
 * Stores basic information such as ID, name, and login status.
 */

public class Hosteska {
    private int id;
    private String meno;
    private boolean prihlasena;

    /**
     * Creates a hostess with specified values.
     *
     * @param id hostess ID
     * @param meno hostess name
     * @param prihlasena login status
     */
    public Hosteska(int id, String meno, boolean prihlasena) {
        this.id = id;
        this.meno = meno;
        this.prihlasena = prihlasena;
    }

    /**
     * Default constructor initializing empty hostess.
     */
    public Hosteska() {
        this(0, "", false);
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns hostess ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets hostess ID.
     *
     * @param id hostess ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns hostess name.
     *
     * @return name
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Sets hostess name.
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
    public boolean isPrihlasena() {
        return prihlasena;
    }

    /**
     * Sets login status.
     *
     * @param prihlasena login state
     */
    public void setPrihlasena(boolean prihlasena) {
        this.prihlasena = prihlasena;
    }

    /**
     * Returns string representation of the hostess.
     *
     * @return string with hostess details
     */
    @Override
    public String toString() {
        return "Hosteska{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", prihlasena=" + prihlasena +
                '}';
    }
}
