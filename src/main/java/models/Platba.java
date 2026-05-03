package models;

import java.time.LocalDateTime;

/**
 * Model class representing a payment (Platba).
 * Stores information such as payment method, status, amount, time, and related account.
 */

public class Platba {
    private int id;
    private String sposob; // "hotovost", "karta"
    private String stav; // "nevybavena", "vybavena", "odmietuta"
    private double suma;
    private LocalDateTime cas;
    private int ucetId;

    /**
     * Creates a payment with specified values.
     *
     * @param id payment ID
     * @param sposob payment method (e.g., hotovost, karta)
     * @param stav payment status (e.g., nevybavena, vybavena, odmietnuta)
     * @param suma amount
     * @param cas payment time
     * @param ucetId related account ID
     */
    public Platba(int id, String sposob, String stav, double suma, LocalDateTime cas, int ucetId) {
        this.id = id;
        this.sposob = sposob;
        this.stav = stav;
        this.suma = suma;
        this.cas = cas;
        this.ucetId = ucetId;
    }

    /**
     * Default constructor initializing payment with default values.
     */
    public Platba() {
        this(0, "hotovost", "nevybavena", 0.0, LocalDateTime.now(), 0);
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns payment ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets payment ID.
     *
     * @param id payment ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns payment method.
     *
     * @return method
     */
    public String getSposob() {
        return sposob;
    }

    /**
     * Sets payment method.
     *
     * @param sposob method
     */
    public void setSposob(String sposob) {
        this.sposob = sposob;
    }

    /**
     * Returns payment status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets payment status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns payment amount.
     *
     * @return amount
     */
    public double getSuma() {
        return suma;
    }

    /**
     * Sets payment amount.
     *
     * @param suma amount
     */
    public void setSuma(double suma) {
        this.suma = suma;
    }

    /**
     * Returns payment time.
     *
     * @return time
     */
    public LocalDateTime getCas() {
        return cas;
    }

    /**
     * Sets payment time.
     *
     * @param cas time
     */
    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    /**
     * Returns related account ID.
     *
     * @return account ID
     */
    public int getUcetId() {
        return ucetId;
    }

    /**
     * Sets related account ID.
     *
     * @param ucetId account ID
     */
    public void setUcetId(int ucetId) {
        this.ucetId = ucetId;
    }

    /**
     * Returns string representation of the payment.
     *
     * @return string with payment details
     */
    @Override
    public String toString() {
        return "Platba{" +
                "id=" + id +
                ", sposob='" + sposob + '\'' +
                ", stav='" + stav + '\'' +
                ", suma=" + suma +
                '}';
    }
}
