package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing the kitchen (Kuchyna).
 * Maintains its status and a list of orders currently being processed.
 */

public class Kuchyna {
    private int id;
    private String stav; // "dostupna", "zauzovana", "uzavreta"
    private List<Objednavka> objednavkyVSpracovani;

    /**
     * Creates a kitchen with specified values.
     *
     * @param id kitchen ID
     * @param stav current status (e.g., dostupna, zauzovana, uzavreta)
     */
    public Kuchyna(int id, String stav) {
        this.id = id;
        this.stav = stav;
        this.objednavkyVSpracovani = new ArrayList<>();
    }

    /**
     * Default constructor initializing kitchen with default values.
     */
    public Kuchyna() {
        this(1, "dostupna");
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns kitchen ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets kitchen ID.
     *
     * @param id kitchen ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns kitchen status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets kitchen status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns list of orders currently being processed.
     *
     * @return list of orders
     */
    public List<Objednavka> getObjednavkyVSpracovani() {
        return objednavkyVSpracovani;
    }

    /**
     * Adds an order to the processing list.
     *
     * @param objednavka order to add
     */
    public void addObjednavka(Objednavka objednavka) {
        this.objednavkyVSpracovani.add(objednavka);
    }

    /**
     * Removes an order from the processing list.
     *
     * @param objednavka order to remove
     */
    public void removeObjednavka(Objednavka objednavka) {
        this.objednavkyVSpracovani.remove(objednavka);
    }

    /**
     * Returns string representation of the kitchen.
     *
     * @return string with kitchen details
     */
    @Override
    public String toString() {
        return "Kuchyna{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", objednavkyVSpracovani=" + objednavkyVSpracovani.size() +
                '}';
    }
}
