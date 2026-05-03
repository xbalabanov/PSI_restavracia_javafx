package models;

public class Stol {
    private int id;
    private String stav; // "volny", "obsadeny", "rezervovany"
    private int kapacita;
    private Objednavka objednavka;
    private Rezervacia rezervacia;

    public Stol(int id, String stav, int kapacita) {
        this.id = id;
        this.stav = stav;
        this.kapacita = kapacita;
        this.objednavka = null;
        this.rezervacia = null;
    }

    public Stol() {
        this(0, "volny", 4);
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

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public Objednavka getObjednavka() {
        return objednavka;
    }

    public void setObjednavka(Objednavka objednavka) {
        this.objednavka = objednavka;
    }

    public Rezervacia getRezervacia() {
        return rezervacia;
    }

    public void setRezervacia(Rezervacia rezervacia) {
        this.rezervacia = rezervacia;
    }

    @Override
    public String toString() {
        return "Stol{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", kapacita=" + kapacita +
                '}';
    }
}
