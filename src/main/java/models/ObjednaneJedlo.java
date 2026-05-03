package models;

public class ObjednaneJedlo {
    private int id;
    private int pocet;
    private double cena;
    private Menu menu;

    public ObjednaneJedlo(int id, int pocet, double cena, Menu menu) {
        this.id = id;
        this.pocet = pocet;
        this.cena = cena;
        this.menu = menu;
    }

    public ObjednaneJedlo() {
        this(0, 1, 0.0, null);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public double getTotalCena() {
        return cena * pocet;
    }

    @Override
    public String toString() {
        return "ObjednaneJedlo{" +
                "id=" + id +
                ", pocet=" + pocet +
                ", cena=" + cena +
                ", menu=" + menu +
                '}';
    }
}
