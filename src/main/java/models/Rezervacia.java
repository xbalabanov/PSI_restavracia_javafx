package models;

import java.time.LocalDateTime;

/**
 * Model class representing a reservation (Rezervacia).
 * Stores information about reservation status, time, table, and customer details.
 */
public class Rezervacia {
    private int id;
    private String stav; // "vytvarana", "potvrdena", "rezervovana", "aktivna", "dokoncena", "zrusena"
    private LocalDateTime cas;
    private int stolId;
    private String zakaznikMeno;
    private String zakaznikKontakt;
    private int pocetOsob;
    private String poznamky;

    /**
     * Creates a reservation with specified values.
     *
     * @param id reservation ID
     * @param stav reservation status (e.g., vytvarana, potvrdena, rezervovana, aktivna, dokoncena, zrusena)
     * @param cas reservation time
     * @param stolId table ID
     * @param zakaznikMeno customer name
     * @param zakaznikKontakt customer contact
     * @param pocetOsob number of people
     * @param poznamky additional notes
     */
    public Rezervacia(int id, String stav, LocalDateTime cas, int stolId,
                      String zakaznikMeno, String zakaznikKontakt, int pocetOsob, String poznamky) {
        this.id = id;
        this.stav = stav;
        this.cas = cas;
        this.stolId = stolId;
        this.zakaznikMeno = zakaznikMeno;
        this.zakaznikKontakt = zakaznikKontakt;
        this.pocetOsob = pocetOsob;
        this.poznamky = poznamky;
    }

    /**
     * Default constructor initializing reservation with default values.
     */
    public Rezervacia() {
        this(0, "vytvarana", LocalDateTime.now(), 0, "", "", 0, "");
    }

    /**
     * Getter and setter methods.
     */
    /**
     * Returns reservation ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets reservation ID.
     *
     * @param id reservation ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns reservation status.
     *
     * @return status
     */
    public String getStav() {
        return stav;
    }

    /**
     * Sets reservation status.
     *
     * @param stav status
     */
    public void setStav(String stav) {
        this.stav = stav;
    }

    /**
     * Returns reservation time.
     *
     * @return time
     */
    public LocalDateTime getCas() {
        return cas;
    }

    /**
     * Sets reservation time.
     *
     * @param cas time
     */
    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    /**
     * Returns table ID.
     *
     * @return table ID
     */
    public int getStolId() {
        return stolId;
    }

    /**
     * Sets table ID.
     *
     * @param stolId table ID
     */
    public void setStolId(int stolId) {
        this.stolId = stolId;
    }

    /**
     * Returns customer name.
     *
     * @return name
     */
    public String getZakaznikMeno() {
        return zakaznikMeno;
    }

    /**
     * Sets customer name.
     *
     * @param zakaznikMeno name
     */
    public void setZakaznikMeno(String zakaznikMeno) {
        this.zakaznikMeno = zakaznikMeno;
    }

    /**
     * Returns customer contact.
     *
     * @return contact
     */
    public String getZakaznikKontakt() {
        return zakaznikKontakt;
    }

    /**
     * Sets customer contact.
     *
     * @param zakaznikKontakt contact
     */
    public void setZakaznikKontakt(String zakaznikKontakt) {
        this.zakaznikKontakt = zakaznikKontakt;
    }

    /**
     * Returns number of people.
     *
     * @return number of people
     */
    public int getPocetOsob() {
        return pocetOsob;
    }

    /**
     * Sets number of people.
     *
     * @param pocetOsob number of people
     */
    public void setPocetOsob(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    /**
     * Returns additional notes.
     *
     * @return notes
     */
    public String getPoznamky() {
        return poznamky;
    }

    /**
     * Sets additional notes.
     *
     * @param poznamky notes
     */
    public void setPoznamky(String poznamky) {
        this.poznamky = poznamky;
    }

    /**
     * Returns string representation of the reservation.
     *
     * @return string with reservation details
     */
    @Override
    public String toString() {
        return "Rezervacia{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", zakaznikMeno='" + zakaznikMeno + '\'' +
                ", pocetOsob=" + pocetOsob +
                '}';
    }
}
