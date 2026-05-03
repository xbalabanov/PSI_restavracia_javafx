package models;

/**
 * Model class representing a menu item.
 * Stores information such as ID, name, price, and availability.
 */

public class Menu {
    private int id;
    private String nazov;
    private double cena;
    private boolean dostupnost;

    /**
     * Creates a menu item with specified values.
     *
     * @param id item ID
     * @param nazov item name
     * @param cena item price
     * @param dostupnost availability status
     */
    public Menu(int id, String nazov, double cena, boolean dostupnost) {
        this.id = id;
        this.nazov = nazov;
        this.cena = cena;
        this.dostupnost = dostupnost;
    }

    /**
     * Default constructor initializing menu item with default values.
     */
    public Menu() {
        this(0, "", 0.0, true);
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns item ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets item ID.
     *
     * @param id item ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns item name.
     *
     * @return name
     */
    public String getNazov() {
        return nazov;
    }

    /**
     * Sets item name.
     *
     * @param nazov name
     */
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Returns item price.
     *
     * @return price
     */
    public double getCena() {
        return cena;
    }

    /**
     * Sets item price.
     *
     * @param cena price
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Returns availability status.
     *
     * @return true if available
     */
    public boolean isDostupnost() {
        return dostupnost;
    }

    /**
     * Sets availability status.
     *
     * @param dostupnost availability
     */
    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    /**
     * Returns string representation of the menu item.
     *
     * @return formatted string with name and price
     */
    @Override
    public String toString() {
        return nazov + " - " + cena + "€";
    }
}
