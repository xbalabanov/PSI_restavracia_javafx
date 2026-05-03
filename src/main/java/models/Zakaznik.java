package models;

public class Zakaznik {
    private int id;
    private String meno;
    private String kontakt;

    public Zakaznik(int id, String meno, String kontakt) {
        this.id = id;
        this.meno = meno;
        this.kontakt = kontakt;
    }

    public Zakaznik() {
        this(0, "", "");
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

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public String toString() {
        return "Zakaznik{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", kontakt='" + kontakt + '\'' +
                '}';
    }
}
