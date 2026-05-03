package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a bill/account (Ucet).
 * Stores information about total amount, discount, time, and ordered items.
 */
public class Ucet {
    private int id;
    private String stav; // "vystaveny", "zaplateny", "zruseny"
    private double suma;
    private LocalDateTime cas;
    private List<ObjednaneJedlo> polozky;
    private double zlava;

    /**
     * Creates a bill with specified values.
     *
     * @param id bill ID
     * @param stav bill status (e.g., vystaveny, zaplateny, zruseny)
     * @param suma total amount
     * @param cas creation time
     */
    public Ucet(int id, String stav, double suma, LocalDateTime cas) {
        this.id = id;
        this.stav = stav;
        this.suma = suma;
        this.cas = cas;
        this.polozky = new ArrayList<>();
        this.zlava = 0.0;
    }

    /**
     * Default constructor initializing bill with default values.
     */
    public Ucet() {
        this(0, "vystaveny", 0.0, LocalDateTime.now());
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns bill ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets bill ID.
     *
     * @param id bill ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns bill status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets bill status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns total amount.
     *
     * @return amount
     */
    public double getSuma() {
        return suma;
    }

    /**
     * Sets total amount.
     *
     * @param suma amount
     */
    public void setSuma(double suma) {
        this.suma = suma;
    }

    /**
     * Returns bill creation time.
     *
     * @return time
     */
    public LocalDateTime getCas() {
        return cas;
    }

    /**
     * Sets bill creation time.
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
     * Adds an item to the bill.
     *
     * @param jedlo item to add
     */
    public void addPolozka(ObjednaneJedlo jedlo) {
        this.polozky.add(jedlo);
    }

    /**
     * Removes an item from the bill.
     *
     * @param jedlo item to remove
     */
    public void removePolozka(ObjednaneJedlo jedlo) {
        this.polozky.remove(jedlo);
    }

    /**
     * Returns discount amount.
     *
     * @return discount
     */
    public double getZlava() {
        return zlava;
    }

    /**
     * Sets discount amount.
     *
     * @param zlava discount
     */
    public void setZlava(double zlava) {
        this.zlava = zlava;
    }

    /**
     * Calculates final amount after discount.
     *
     * @return final amount
     */
    public double getFinalniSuma() {
        return suma - zlava;
    }

    /**
     * Returns string representation of the bill.
     *
     * @return string with bill details
     */
    @Override
    public String toString() {
        return "Ucet{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", suma=" + suma +
                ", zlava=" + zlava +
                '}';
    }
}
