package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Objednavka {
    private int id;
    private int stav; // 0 - vytvarana, 1 - potvrdena, 2 - v spracovani, 3 - vybavena
    private LocalDateTime cas;
    private List<ObjednaneJedlo> polozky;
    private Stol stol;

    public Objednavka(int id, int stav, LocalDateTime cas, Stol stol) {
        this.id = id;
        this.stav = stav;
        this.cas = cas;
        this.stol = stol;
        this.polozky = new ArrayList<>();
    }

    public Objednavka() {
        this(0, 0, LocalDateTime.now(), null);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStav() {
        return stav;
    }

    public void setStav(int stav) {
        this.stav = stav;
    }

    public LocalDateTime getCas() {
        return cas;
    }

    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    public List<ObjednaneJedlo> getPolozky() {
        return polozky;
    }

    public void addPolozka(ObjednaneJedlo jedlo) {
        this.polozky.add(jedlo);
    }

    public void removePolozka(ObjednaneJedlo jedlo) {
        this.polozky.remove(jedlo);
    }

    public Stol getStol() {
        return stol;
    }

    public void setStol(Stol stol) {
        this.stol = stol;
    }

    public double getTotalCena() {
        double total = 0;
        for (ObjednaneJedlo jedlo : polozky) {
            total += jedlo.getTotalCena();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Objednavka{" +
                "id=" + id +
                ", stav=" + stav +
                ", cas=" + cas +
                ", polozky=" + polozky.size() +
                '}';
    }
}
