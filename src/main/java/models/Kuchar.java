package models;

public class Kuchar {
    private int id;
    private String meno;
    private String stav; // "dostupny", "pripravauje", "odpociva"

    public Kuchar(int id, String meno, String stav) {
        this.id = id;
        this.meno = meno;
        this.stav = stav;
    }

    public Kuchar() {
        this(0, "", "dostupny");
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

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    @Override
    public String toString() {
        return "Kuchar{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", stav='" + stav + '\'' +
                '}';
    }
}
