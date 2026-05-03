package models;

import java.util.ArrayList;
import java.util.List;

public class Kuchyna {
    private int id;
    private String stav; // "dostupna", "zauzovana", "uzavreta"
    private List<Objednavka> objednavkyVSpracovani;

    public Kuchyna(int id, String stav) {
        this.id = id;
        this.stav = stav;
        this.objednavkyVSpracovani = new ArrayList<>();
    }

    public Kuchyna() {
        this(1, "dostupna");
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

    public List<Objednavka> getObjednavkyVSpracovani() {
        return objednavkyVSpracovani;
    }

    public void addObjednavka(Objednavka objednavka) {
        this.objednavkyVSpracovani.add(objednavka);
    }

    public void removeObjednavka(Objednavka objednavka) {
        this.objednavkyVSpracovani.remove(objednavka);
    }

    @Override
    public String toString() {
        return "Kuchyna{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", objednavkyVSpracovani=" + objednavkyVSpracovani.size() +
                '}';
    }
}
