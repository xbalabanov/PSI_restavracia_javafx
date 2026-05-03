package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a cash register (Pokladna).
 * Manages its status, current balance, and list of transactions.
 */

public class Pokladna {
    private int id;
    private String stav; // "aktivna", "uzavreta"
    private double zostavok;
    private List<Platba> transakcie;

    /**
     * Creates a cash register with specified values.
     *
     * @param id register ID
     * @param stav current status (e.g., aktivna, uzavreta)
     * @param zostavok current balance
     */
    public Pokladna(int id, String stav, double zostavok) {
        this.id = id;
        this.stav = stav;
        this.zostavok = zostavok;
        this.transakcie = new ArrayList<>();
    }

    /**
     * Default constructor initializing register with default values.
     */
    public Pokladna() {
        this(1, "aktivna", 0.0);
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns register ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets register ID.
     *
     * @param id register ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns register status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets register status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns current balance.
     *
     * @return balance
     */
    public double getZostavok() {
        return zostavok;
    }

    /**
     * Sets current balance.
     *
     * @param zostavok balance
     */
    public void setZostavok(double zostavok) {
        this.zostavok = zostavok;
    }

    /**
     * Returns list of transactions.
     *
     * @return list of payments
     */
    public List<Platba> getTransakcie() {
        return transakcie;
    }

    /**
     * Adds a payment transaction and updates balance.
     *
     * @param platba payment to add
     */
    public void addTransakcia(Platba platba) {
        this.transakcie.add(platba);
        this.zostavok += platba.getSuma();
    }

    /**
     * Returns string representation of the cash register.
     *
     * @return string with register details
     */
    @Override
    public String toString() {
        return "Pokladna{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", zostavok=" + zostavok +
                '}';
    }
}
