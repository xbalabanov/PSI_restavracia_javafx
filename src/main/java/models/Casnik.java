package models;

public class Casnik {
    private int id;
    private String meno;
    private boolean prihlaseny;

    public Casnik(int id, String meno, boolean prihlaseny) {
        this.id = id;
        this.meno = meno;
        this.prihlaseny = prihlaseny;
    }

    public Casnik() {
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

    public boolean isPrihlaseny() {
        return prihlaseny;
    }

    public void setPrihlaseny(boolean prihlaseny) {
        this.prihlaseny = prihlaseny;
    }

    @Override
    public String toString() {
        return "Casnik{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", prihlaseny=" + prihlaseny +
                '}';
    }
}
