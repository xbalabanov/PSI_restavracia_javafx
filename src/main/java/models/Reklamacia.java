package models;

import java.time.LocalDateTime;

public class Reklamacia {
    private int id;
    private String dovod;
    private String stav; // "nova", "preskumavana", "schvalena", "zamietnuta"
    private LocalDateTime cas;
    private int objednavkaId;
    private int zakaznikId;
    private String vysledok; // "vymena", "vracanie_penazi", "odmietuta"

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

    public Reklamacia() {
        this(0, "", "nova", LocalDateTime.now(), 0, 0, "");
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDovod() {
        return dovod;
    }

    public void setDovod(String dovod) {
        this.dovod = dovod;
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

    public int getObjednavkaId() {
        return objednavkaId;
    }

    public void setObjednavkaId(int objednavkaId) {
        this.objednavkaId = objednavkaId;
    }

    public int getZakaznikId() {
        return zakaznikId;
    }

    public void setZakaznikId(int zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    public String getVysledok() {
        return vysledok;
    }

    public void setVysledok(String vysledok) {
        this.vysledok = vysledok;
    }

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
