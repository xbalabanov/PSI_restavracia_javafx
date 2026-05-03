package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing an order (Objednavka).
 * Contains information about order status, time, table, and ordered items.
 */

public class Objednavka {
    private int id;
    private int stav; // 0 - vytvarana, 1 - potvrdena, 2 - v spracovani, 3 - vybavena
    private LocalDateTime cas;
    private List<ObjednaneJedlo> polozky;
    private Stol stol;

    /**
     * Creates an order with specified values.
     *
     * @param id order ID
     * @param stav order status (0 - vytvarana, 1 - potvrdena, 2 - v spracovani, 3 - vybavena)
     * @param cas creation time
     * @param stol associated table
     */
    public Objednavka(int id, int stav, LocalDateTime cas, Stol stol) {
        this.id = id;
        this.stav = stav;
        this.cas = cas;
        this.stol = stol;
        this.polozky = new ArrayList<>();
    }

    /**
     * Default constructor initializing order with default values.
     */
    public Objednavka() {
        this(0, 0, LocalDateTime.now(), null);
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns order ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets order ID.
     *
     * @param id order ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns order status.
     *
     * @return status
     */
    public int getStav() {
        return stav;
    }

    /**
     * Sets order status.
     *
     * @param stav status
     */
    public void setStav(int stav) {
        this.stav = stav;
    }

    /**
     * Returns order creation time.
     *
     * @return time
     */
    public LocalDateTime getCas() {
        return cas;
    }

    /**
     * Sets order creation time.
     *
     * @param cas time
     */
    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    /**
     * Returns list of ordered items.
     *
     * @return list of items
     */
    public List<ObjednaneJedlo> getPolozky() {
        return polozky;
    }

    /**
     * Adds an item to the order.
     *
     * @param jedlo item to add
     */
    public void addPolozka(ObjednaneJedlo jedlo) {
        this.polozky.add(jedlo);
    }

    /**
     * Removes an item from the order.
     *
     * @param jedlo item to remove
     */
    public void removePolozka(ObjednaneJedlo jedlo) {
        this.polozky.remove(jedlo);
    }

    /**
     * Returns associated table.
     *
     * @return table
     */
    public Stol getStol() {
        return stol;
    }

    /**
     * Sets associated table.
     *
     * @param stol table
     */
    public void setStol(Stol stol) {
        this.stol = stol;
    }

    /**
     * Calculates total price of the order.
     *
     * @return total price
     */
    public double getTotalCena() {
        double total = 0;
        for (ObjednaneJedlo jedlo : polozky) {
            total += jedlo.getTotalCena();
        }
        return total;
    }

    /**
     * Returns string representation of the order.
     *
     * @return string with order details
     */
    @Override
    public String toString() {
        return "Objednavka{" +
                "id=" + id +
                ", stav=" + stav +
                ", cas=" + cas +
                ", polozky=" + polozky.size() +
                '}';
    }
}
