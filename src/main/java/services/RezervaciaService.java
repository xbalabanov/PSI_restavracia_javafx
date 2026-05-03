package services;

import models.Rezervacia;
import models.Stol;
import java.time.LocalDateTime;

public class RezervaciaService {

    // UC03 - Rezervácia stola (UC03a - osobne, UC03b - telefonicky)
    public Rezervacia createRezervacia(String zakaznikMeno, String zakaznikKontakt,
            LocalDateTime cas, int pocetOsob, String poznamky) {
        Rezervacia rezervacia = new Rezervacia();
        rezervacia.setZakaznikMeno(zakaznikMeno);
        rezervacia.setZakaznikKontakt(zakaznikKontakt);
        rezervacia.setCas(cas);
        rezervacia.setPocetOsob(pocetOsob);
        rezervacia.setPoznamky(poznamky);
        rezervacia.setStav("vytvarana");
        return rezervacia;
    }

    public boolean checkTableAvailability(Stol stol, LocalDateTime cas) {
        // Zjednodušená kontrola - stôl musí byť voľný
        return stol.getStav().equals("volny");
    }

    public void assignTableToRezervacia(Rezervacia rezervacia, Stol stol) {
        rezervacia.setStolId(stol.getId());
        rezervacia.setStav("potvrdena");
        stol.setStav("rezervovany");
        System.out.println("Stôl " + stol.getId() + " je rezervovaný");
    }

    public void confirmRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("rezervovana");
        System.out.println("Rezervácia potvrdená");
    }

    public void cancelRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("zrusena");
        System.out.println("Rezervácia zrušená");
    }

    public void activateRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("aktivna");
        System.out.println("Zákazník je usadený - rezervácia aktivná");
    }

    public void completeRezerbacia(Rezervacia rezervacia) {
        rezervacia.setStav("dokoncena");
        System.out.println("Rezervácia dokončená");
    }
}
