package models;

/**
 * Model class representing an ordered item (ObjednaneJedlo).
 * Contains information about quantity, price, and associated menu item.
 */

public class ObjednaneJedlo {
    private int id;
    private int pocet;
    private double cena;
    private Menu menu;

    /**
     * Creates an ordered item with specified values.
     *
     * @param id item ID
     * @param pocet quantity
     * @param cena price per item
     * @param menu associated menu item
     */
    public ObjednaneJedlo(int id, int pocet, double cena, Menu menu) {
        this.id = id;
        this.pocet = pocet;
        this.cena = cena;
        this.menu = menu;
    }

    /**
     * Default constructor initializing ordered item with default values.
     */
    public ObjednaneJedlo() {
        this(0, 1, 0.0, null);
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
     * Returns quantity.
     *
     * @return quantity
     */
    public int getPocet() {
        return pocet;
    }

    /**
     * Sets quantity.
     *
     * @param pocet quantity
     */
    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    /**
     * Returns price per item.
     *
     * @return price
     */
    public double getCena() {
        return cena;
    }

    /**
     * Sets price per item.
     *
     * @param cena price
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Returns associated menu item.
     *
     * @return menu item
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Sets associated menu item.
     *
     * @param menu menu item
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * Calculates total price for this item.
     *
     * @return total price (price * quantity)
     */
    public double getTotalCena() {
        return cena * pocet;
    }

    /**
     * Returns string representation of the ordered item.
     *
     * @return string with item details
     */
    @Override
    public String toString() {
        return "ObjednaneJedlo{" +
                "id=" + id +
                ", pocet=" + pocet +
                ", cena=" + cena +
                ", menu=" + menu +
                '}';
    }
}
