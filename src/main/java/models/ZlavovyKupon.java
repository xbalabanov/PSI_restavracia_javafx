package models;

import java.time.LocalDate;

/**
 * Model class representing a discount coupon (ZlavovyKupon).
 * Contains information about code, discount percentage, expiration date, and usage status.
 */
public class ZlavovyKupon {
    private String kod;
    private double zlavaProcent;
    private LocalDate platnostDo;
    private boolean pouzity;

    /**
     * Creates a discount coupon with specified values.
     *
     * @param kod coupon code
     * @param zlavaProcent discount percentage
     * @param platnostDo expiration date
     * @param pouzity usage status
     */
    public ZlavovyKupon(String kod, double zlavaProcent, LocalDate platnostDo, boolean pouzity) {
        this.kod = kod;
        this.zlavaProcent = zlavaProcent;
        this.platnostDo = platnostDo;
        this.pouzity = pouzity;
    }

    /**
     * Default constructor initializing coupon with default values.
     */
    public ZlavovyKupon() {
        this("", 0.0, LocalDate.now(), false);
    }

    /**
     * Getter and setter methods.
     */

    /**
     * Returns coupon code.
     *
     * @return code
     */
    public String getKod() {
        return kod;
    }

    /**
     * Sets coupon code.
     *
     * @param kod code
     */
    public void setKod(String kod) {
        this.kod = kod;
    }

    /**
     * Returns discount percentage.
     *
     * @return percentage
     */
    public double getZlavaProcent() {
        return zlavaProcent;
    }

    /**
     * Sets discount percentage.
     *
     * @param zlavaProcent percentage
     */
    public void setZlavaProcent(double zlavaProcent) {
        this.zlavaProcent = zlavaProcent;
    }

    /**
     * Returns expiration date.
     *
     * @return date
     */
    public LocalDate getPlatnostDo() {
        return platnostDo;
    }

    /**
     * Sets expiration date.
     *
     * @param platnostDo date
     */
    public void setPlatnostDo(LocalDate platnostDo) {
        this.platnostDo = platnostDo;
    }

    /**
     * Returns usage status.
     *
     * @return true if used
     */
    public boolean isPouzity() {
        return pouzity;
    }

    /**
     * Sets usage status.
     *
     * @param pouzity status
     */
    public void setPouzity(boolean pouzity) {
        this.pouzity = pouzity;
    }

    /**
     * Checks if the coupon is valid (not expired and not used).
     *
     * @return true if valid
     */
    public boolean isValid() {
        return platnostDo.isAfter(LocalDate.now()) && !pouzity;
    }

    /**
     * Returns string representation of the coupon.
     *
     * @return string with coupon details
     */
    @Override
    public String toString() {
        return "ZlavovyKupon{" +
                "kod='" + kod + '\'' +
                ", zlavaProcent=" + zlavaProcent +
                ", platnostDo=" + platnostDo +
                '}';
    }
}
