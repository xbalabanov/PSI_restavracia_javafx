package models;

import java.util.ArrayList;
import java.util.List;

public class Pokladna {
    private int id;
    private String stav; // "aktivna", "uzavreta"
    private double zostavok;
    private List<Platba> transakcie;

    public Pokladna(int id, String stav, double zostavok) {
        this.id = id;
        this.stav = stav;
        this.zostavok = zostavok;
        this.transakcie = new ArrayList<>();
    }

    public Pokladna() {
        this(1, "aktivna", 0.0);
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

    public double getZostavok() {
        return zostavok;
    }

    public void setZostavok(double zostavok) {
        this.zostavok = zostavok;
    }

    public List<Platba> getTransakcie() {
        return transakcie;
    }

    public void addTransakcia(Platba platba) {
        this.transakcie.add(platba);
        this.zostavok += platba.getSuma();
    }

    @Override
    public String toString() {
        return "Pokladna{" +
                "id=" + id +
                ", stav='" + stav + '\'' +
                ", zostavok=" + zostavok +
                '}';
    }
}
