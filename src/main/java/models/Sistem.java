
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Central system model (Sistem) representing the whole restaurant system.
 * Aggregates orders, reservations, tables, menu, kitchen, and cash register.
 */

public class Sistem {
    private String stav; // "funkcny", "nefunkcny"
    private List<Objednavka> objednavky;
    private List<Rezervacia> rezervacie;
    private List<Stol> stoly;
    private List<Menu> menu;
    private Kuchyna kuchyna;
    private Pokladna pokladna;

    /**
     * Initializes the system with default values and empty collections.
     */
    public Sistem() {
        this.stav = "funkcny";
        this.objednavky = new ArrayList<>();
        this.rezervacie = new ArrayList<>();
        this.stoly = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.kuchyna = new Kuchyna();
        this.pokladna = new Pokladna();
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns system status.
     *
     * @return status (e.g., funkcny, nefunkcny)
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets system status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns all orders in the system.
     *
     * @return list of orders
     */
    public List<Objednavka> getObjednavky() {
        return objednavky;
    }

    /**
     * Adds an order to the system.
     *
     * @param objednavka order to add
     */
    public void addObjednavka(Objednavka objednavka) {
        this.objednavky.add(objednavka);
    }

    /**
     * Returns all reservations.
     *
     * @return list of reservations
     */
    public List<Rezervacia> getRezerbacie() {
        return rezervacie;
    }

    /**
     * Adds a reservation to the system.
     *
     * @param rezervacia reservation to add
     */
    public void addRezerbacia(Rezervacia rezervacia) {
        this.rezervacie.add(rezervacia);
    }

    /**
     * Returns all tables.
     *
     * @return list of tables
     */
    public List<Stol> getStoly() {
        return stoly;
    }

    /**
     * Adds a table to the system.
     *
     * @param stol table to add
     */
    public void addStol(Stol stol) {
        this.stoly.add(stol);
    }

    /**
     * Returns all menu items.
     *
     * @return list of menu items
     */
    public List<Menu> getMenu() {
        return menu;
    }

    /**
     * Adds a menu item.
     *
     * @param item menu item to add
     */
    public void addMenuItem(Menu item) {
        this.menu.add(item);
    }

    /**
     * Returns kitchen instance.
     *
     * @return kitchen
     */
    public Kuchyna getKuchyna() {
        return kuchyna;
    }

    /**
     * Sets kitchen instance.
     *
     * @param kuchyna kitchen
     */
    public void setKuchyna(Kuchyna kuchyna) {
        this.kuchyna = kuchyna;
    }

    /**
     * Returns cash register.
     *
     * @return cash register
     */
    public Pokladna getPokladna() {
        return pokladna;
    }

    /**
     * Sets cash register.
     *
     * @param pokladna cash register
     */
    public void setPokladna(Pokladna pokladna) {
        this.pokladna = pokladna;
    }

    /**
     * Returns string representation of the system.
     *
     * @return string with system summary
     */
    @Override
    public String toString() {
        return "Sistem{" +
                "stav='" + stav + '\'' +
                ", objednavky=" + objednavky.size() +
                ", stoly=" + stoly.size() +
                '}';
    }
}
