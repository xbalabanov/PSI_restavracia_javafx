package models;

import java.time.LocalDate;

public class ZlavovyKupon {
    private String kod;
    private double zlavaProcent;
    private LocalDate platnostDo;
    private boolean pouzity;

    public ZlavovyKupon(String kod, double zlavaProcent, LocalDate platnostDo, boolean pouzity) {
        this.kod = kod;
        this.zlavaProcent = zlavaProcent;
        this.platnostDo = platnostDo;
        this.pouzity = pouzity;
    }

    public ZlavovyKupon() {
        this("", 0.0, LocalDate.now(), false);
    }

    // Getters and Setters
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public double getZlavaProcent() {
        return zlavaProcent;
    }

    public void setZlavaProcent(double zlavaProcent) {
        this.zlavaProcent = zlavaProcent;
    }

    public LocalDate getPlatnostDo() {
        return platnostDo;
    }

    public void setPlatnostDo(LocalDate platnostDo) {
        this.platnostDo = platnostDo;
    }

    public boolean isPouzity() {
        return pouzity;
    }

    public void setPouzity(boolean pouzity) {
        this.pouzity = pouzity;
    }

    public boolean isValid() {
        return platnostDo.isAfter(LocalDate.now()) && !pouzity;
    }

    @Override
    public String toString() {
        return "ZlavovyKupon{" +
                "kod='" + kod + '\'' +
                ", zlavaProcent=" + zlavaProcent +
                ", platnostDo=" + platnostDo +
                '}';
    }
}
