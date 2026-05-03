package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ucet {
    private int id;
    private String stav; // "vystaveny", "zaplateny", "zruseny"
    private double suma;
    private LocalDateTime cas;
    private List<ObjednaneJedlo> polozky;
    private double zlava;

    public Ucet(int id, String stav, double suma, LocalDateTime cas) {
        this.id = id;
        this.stav = stav;
        this.suma = suma;
        this.cas = cas;
        this.polozky = new ArrayList<>();
        this.zlava = 0.0;
    }

    public Ucet() {
        this(0, "vystaveny", 0.0, LocalDateTime.now());
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

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
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

    public double getZlava() {
        return zlava;
    }

    public void setZlava(double zlava) {
        this.zlava = zlava;
    }

    public double getFinalniSuma() {
        return suma - zlava;
    }

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
