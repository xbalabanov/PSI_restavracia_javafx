package models;

/**
 * Model class representing a table (Stol) in the restaurant.
 * Stores information about its status, capacity, and assigned order or reservation.
 */
public class Stol {
    private int id;
    private String stav; // "volny", "obsadeny", "rezervovany"
    private int kapacita;
    private Objednavka objednavka;
    private Rezervacia rezervacia;

    /**
     * Creates a table with specified values.
     *
     * @param id table ID
     * @param stav table status (e.g., volny, obsadeny, rezervovany)
     * @param kapacita seating capacity
     */
    public Stol(int id, String stav, int kapacita) {
        this.id = id;
        this.stav = stav;
        this.kapacita = kapacita;
        this.objednavka = null;
        this.rezervacia = null;
    }

    /**
     * Default constructor initializing table with default values.
     */
    public Stol() {
        this(0, "volny", 4);
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns table ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets table ID.
     *
     * @param id table ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns table status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets table status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns seating capacity.
     *
     * @return capacity
     */
    public int getKapacita() {
        return kapacita;
    }

    /**
     * Sets seating capacity.
     *
     * @param kapacita capacity
     */
    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    /**
     * Returns assigned order.
     *
     * @return order
     */
    public Objednavka getObjednavka() {
        return objednavka;
    }

    /**
     * Sets assigned order.
     *
     * @param objednavka order
     */
    public void setObjednavka(Objednavka objednavka) {
        this.objednavka = objednavka;
    }

    /**
     * Returns assigned reservation.
     *
     * @return reservation
     */
    public Rezervacia getRezervacia() {
        return rezervacia;
    }

    /**
     * Sets assigned reservation.
     *
     * @param rezervacia reservation
     */
    public void setRezervacia(Rezervacia rezervacia) {
        this.rezervacia = rezervacia;
    }

    /**
     * Returns string representation of the table.
     *
     * @return string with table details
     */
    @Override
    public String toString() {
        return "Stol{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", kapacita=" + kapacita +
                '}';
    }
}
