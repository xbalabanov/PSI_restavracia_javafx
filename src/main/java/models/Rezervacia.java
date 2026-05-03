package models;

import java.time.LocalDateTime;

public class Rezervacia {
    private int id;
    private String stav; // "vytvarana", "potvrdena", "rezervovana", "aktivna", "dokoncena", "zrusena"
    private LocalDateTime cas;
    private int stolId;
    private String zakaznikMeno;
    private String zakaznikKontakt;
    private int pocetOsob;
    private String poznamky;

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

    public Rezervacia() {
        this(0, "vytvarana", LocalDateTime.now(), 0, "", "", 0, "");
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public LocalDateTime getCas() {
        return cas;
    }

    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    public int getStolId() {
        return stolId;
    }

    public void setStolId(int stolId) {
        this.stolId = stolId;
    }

    public String getZakaznikMeno() {
        return zakaznikMeno;
    }

    public void setZakaznikMeno(String zakaznikMeno) {
        this.zakaznikMeno = zakaznikMeno;
    }

    public String getZakaznikKontakt() {
        return zakaznikKontakt;
    }

    public void setZakaznikKontakt(String zakaznikKontakt) {
        this.zakaznikKontakt = zakaznikKontakt;
    }

    public int getPocetOsob() {
        return pocetOsob;
    }

    public void setPocetOsob(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    public String getPoznamky() {
        return poznamky;
    }

    public void setPoznamky(String poznamky) {
        this.poznamky = poznamky;
    }

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
