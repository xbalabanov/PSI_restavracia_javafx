package models;

public class Hosteska {
    private int id;
    private String meno;
    private boolean prihlasena;

    public Hosteska(int id, String meno, boolean prihlasena) {
        this.id = id;
        this.meno = meno;
        this.prihlasena = prihlasena;
    }

    public Hosteska() {
        this(0, "", false);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public boolean isPrihlasena() {
        return prihlasena;
    }

    public void setPrihlasena(boolean prihlasena) {
        this.prihlasena = prihlasena;
    }

    @Override
    public String toString() {
        return "Hosteska{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", prihlasena=" + prihlasena +
                '}';
    }
}
