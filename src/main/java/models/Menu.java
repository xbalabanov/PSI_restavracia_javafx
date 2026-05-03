package models;

public class Menu {
    private int id;
    private String nazov;
    private double cena;
    private boolean dostupnost;

    public Menu(int id, String nazov, double cena, boolean dostupnost) {
        this.id = id;
        this.nazov = nazov;
        this.cena = cena;
        this.dostupnost = dostupnost;
    }

    public Menu() {
        this(0, "", 0.0, true);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    @Override
    public String toString() {
        return nazov + " - " + cena + "€";
    }
}
