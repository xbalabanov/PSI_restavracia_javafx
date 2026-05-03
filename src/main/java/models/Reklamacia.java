package models;

import java.time.LocalDateTime;

/**
 * Model class representing a complaint (Reklamacia).
 * Stores reason, status, timestamps, related order/customer, and result.
 */
public class Reklamacia {
    private int id;
    private String dovod;
    private String stav; // "nova", "preskumavana", "schvalena", "zamietnuta"
    private LocalDateTime cas;
    private int objednavkaId;
    private int zakaznikId;
    private String vysledok; // "vymena", "vracanie_penazi", "odmietuta"

    /**
     * Creates a complaint with specified values.
     *
     * @param id complaint ID
     * @param dovod reason of complaint
     * @param stav status (e.g., nova, preskumavana, schvalena, zamietnuta)
     * @param cas creation time
     * @param objednavkaId related order ID
     * @param zakaznikId related customer ID
     * @param vysledok result (e.g., vymena, vracanie_penazi, odmietnuta)
     */
    public Reklamacia(int id, String dovod, String stav, LocalDateTime cas,
                      int objednavkaId, int zakaznikId, String vysledok) {
        this.id = id;
        this.dovod = dovod;
        this.stav = stav;
        this.cas = cas;
        this.objednavkaId = objednavkaId;
        this.zakaznikId = zakaznikId;
        this.vysledok = vysledok;
    }

    /**
     * Default constructor initializing complaint with default values.
     */
    public Reklamacia() {
        this(0, "", "nova", LocalDateTime.now(), 0, 0, "");
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns complaint ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets complaint ID.
     *
     * @param id complaint ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns complaint reason.
     *
     * @return reason
     */
    public String getDovod() {
        return dovod;
    }

    /**
     * Sets complaint reason.
     *
     * @param dovod reason
     */
    public void setDovod(String dovod) {
        this.dovod = dovod;
    }

    /**
     * Returns complaint status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets complaint status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns complaint creation time.
     *
     * @return time
     */
    public LocalDateTime getCas() {
        return cas;
    }

    /**
     * Sets complaint creation time.
     *
     * @param cas time
     */
    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    /**
     * Returns related order ID.
     *
     * @return order ID
     */
    public int getObjednavkaId() {
        return objednavkaId;
    }

    /**
     * Sets related order ID.
     *
     * @param objednavkaId order ID
     */
    public void setObjednavkaId(int objednavkaId) {
        this.objednavkaId = objednavkaId;
    }

    /**
     * Returns related customer ID.
     *
     * @return customer ID
     */
    public int getZakaznikId() {
        return zakaznikId;
    }

    /**
     * Sets related customer ID.
     *
     * @param zakaznikId customer ID
     */
    public void setZakaznikId(int zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    /**
     * Returns complaint result.
     *
     * @return result
     */
    public String getVysledok() {
        return vysledok;
    }

    /**
     * Sets complaint result.
     *
     * @param vysledok result
     */
    public void setVysledok(String vysledok) {
        this.vysledok = vysledok;
    }

    /**
     * Returns string representation of the complaint.
     *
     * @return string with complaint details
     */
    @Override
    public String toString() {
        return "Reklamacia{" +
                "id=" + id +
                ", dovod='" + dovod + '\'' +
                ", stav='" + stav + '\'' +
                ", vysledok='" + vysledok + '\'' +
                '}';
    }
}
