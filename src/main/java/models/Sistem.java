package models;

import java.util.ArrayList;
import java.util.List;

public class Sistem {
    private String stav; // "funkcny", "nefunkcny"
    private List<Objednavka> objednavky;
    private List<Rezervacia> rezervacie;
    private List<Stol> stoly;
    private List<Menu> menu;
    private Kuchyna kuchyna;
    private Pokladna pokladna;

    public Sistem() {
        this.stav = "funkcny";
        this.objednavky = new ArrayList<>();
        this.rezervacie = new ArrayList<>();
        this.stoly = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.kuchyna = new Kuchyna();
        this.pokladna = new Pokladna();
    }

    // Getters and Setters
    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public List<Objednavka> getObjednavky() {
        return objednavky;
    }

    public void addObjednavka(Objednavka objednavka) {
        this.objednavky.add(objednavka);
    }

    public List<Rezervacia> getRezerbacie() {
        return rezervacie;
    }

    public void addRezerbacia(Rezervacia rezervacia) {
        this.rezervacie.add(rezervacia);
    }

    public List<Stol> getStoly() {
        return stoly;
    }

    public void addStol(Stol stol) {
        this.stoly.add(stol);
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void addMenuItem(Menu item) {
        this.menu.add(item);
    }

    public Kuchyna getKuchyna() {
        return kuchyna;
    }

    public void setKuchyna(Kuchyna kuchyna) {
        this.kuchyna = kuchyna;
    }

    public Pokladna getPokladna() {
        return pokladna;
    }

    public void setPokladna(Pokladna pokladna) {
        this.pokladna = pokladna;
    }

    @Override
    public String toString() {
        return "Sistem{" +
                "stav='" + stav + '\'' +
                ", objednavky=" + objednavky.size() +
                ", stoly=" + stoly.size() +
                '}';
    }
}
