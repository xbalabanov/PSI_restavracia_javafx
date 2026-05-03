package models;

import java.time.LocalDateTime;

public class Platba {
    private int id;
    private String sposob; // "hotovost", "karta"
    private String stav; // "nevybavena", "vybavena", "odmietuta"
    private double suma;
    private LocalDateTime cas;
    private int ucetId;

    public Platba(int id, String sposob, String stav, double suma, LocalDateTime cas, int ucetId) {
        this.id = id;
        this.sposob = sposob;
        this.stav = stav;
        this.suma = suma;
        this.cas = cas;
        this.ucetId = ucetId;
    }

    public Platba() {
        this(0, "hotovost", "nevybavena", 0.0, LocalDateTime.now(), 0);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSposob() {
        return sposob;
    }

    public void setSposob(String sposob) {
        this.sposob = sposob;
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

    public int getUcetId() {
        return ucetId;
    }

    public void setUcetId(int ucetId) {
        this.ucetId = ucetId;
    }

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
